package io.projectZ.orchestrator.infrastructure.config;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 10:40 AM
*/

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.ping.PingManager;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//todo : scope must be refined

@Component
public class XmppConnection {

    @Value("${xmpp.connection.host}")
    private String xmppHost;
    @Value("${xmpp.connection.port}")
    private Integer xmppHostPort;
    @Value("${xmpp.connection.domainName}")
    private String xmppDomainName;
    private Logger logger = LogManager.getLogger(XmppConnection.class);
    private static Map<String, AbstractXMPPConnection> establishedConnectionMap = new HashMap<>();

    public synchronized AbstractXMPPConnection connection(String username , String pass) {
        logger.info("xmpp connecting for {} ..." , username);
        XMPPTCPConnectionConfiguration config =
                null;
        if (establishedConnectionMap.get(username) == null || !establishedConnectionMap.get(username).isConnected()) {
            try {
                config = XMPPTCPConnectionConfiguration.builder()
                        .setXmppDomain(xmppDomainName)
                        .setHost(xmppHost)
                        .setPort(xmppHostPort)
                        .setUsernameAndPassword(username, pass)
                        .setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled)
                        .build();
            } catch (XmppStringprepException e) {
                throw new RuntimeException(e);
            }

            AbstractXMPPConnection connection =
                    new XMPPTCPConnection(config);
            SASLAuthentication.unBlacklistSASLMechanism("PLAIN");
            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-256");
            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-512");
            SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
            SASLAuthentication.blacklistSASLMechanism("CRAM-MD5");

            System.out.println(connection.getFeature("mechanisms", "urn:ietf:params:xml:ns:xmpp-sasl"));
            try {
                connection.connect();
                logger.info("xmpp user {} successfully connected" , username);
                connection.login();
                logger.info("xmpp {} Logged In" , username);
                establishedConnectionMap.put(username , connection);

            } catch (SmackException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (XMPPException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pingConnection(connection);
            return connection;
        } else
            return establishedConnectionMap.get(username);
    }

    public static AbstractXMPPConnection getConnection(String username) {
        if (establishedConnectionMap.get(username) != null) {
            if (establishedConnectionMap.get(username).isConnected()) {
                return establishedConnectionMap.get(username);
            } else {
                establishedConnectionMap.remove(username);
            }
        }
        return null;
    }

    private void pingConnection(AbstractXMPPConnection connection){
        PingManager pingManager = PingManager.getInstanceFor(connection);
        pingManager.setPingInterval(300);
    }
}


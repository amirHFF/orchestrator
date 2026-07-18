package io.projectZ.orchestrator.infrastructure.config;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/18/2026 - 2:53 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.in.xmpp.XmppClientListener;
import io.projectZ.orchestrator.infrastructure.adapter.out.keycloak.KeycloakTokenGateway;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.AiRestClient;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.KeycloakAdminClient;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.AiResponse;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.ChatTalkRequest;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ApplicationInitializer {

    @Autowired
    private  XmppConnection xmppConnection;
    @Autowired
    private XmppClientListener xmppClientListener;
    @Autowired
    KeycloakTokenGateway<String> keycloakTokenGateway;
    @Autowired
    private AiRestClient aiRestClient;
    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {

//        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJPSlh4RUk0YTY0c2V1eHU2Rmk3WjZUdUhIQjdBc2luZ0JPS2ljZUJTU1EwIn0.eyJleHAiOjE3ODQzOTI4OTAsImlhdCI6MTc4NDM5MTk5MCwianRpIjoib25ydHJvOjEyZmViMDNhLTE1MGItYTM3NC0zZDQzLWY4ZTQxMzU2Nzg3MSIsImlzcyI6Imh0dHA6Ly8xMzAuMTg1LjEyMS4xNzM6ODA4MS9yZWFsbXMvcHJvamVjdC16IiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjYyNDcxNWQzLTMzMWItNDU5NC1hMjdjLTk5YjJjY2YzNGI1ZSIsInR5cCI6IkJlYXJlciIsImF6cCI6InotY2hhdCIsInNpZCI6IjRXYlo5Y09RQWt1UEtLLTZrcU5TMXB3RiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXByb2plY3QteiIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiY2hhdCBib3QiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJjaGF0LWJvdCIsImdpdmVuX25hbWUiOiJjaGF0IiwiZmFtaWx5X25hbWUiOiJib3QiLCJlbWFpbCI6ImVmdGVraGFyZWFmYWdoLjE5MTAyQGdtYWlsLmNvbSJ9.rC2MUY2ifH1S_siVDzKqjFCvNvVkgih0yF5Nkqp1grulu6sva13ovJHpwaKEDU-L4i1hzgJbKOV_5rtJu02-PAn0j3bmTbqqEWJSAnF-zD_xu4cXGRZW9o05f-FT3rgBVjx1iF2-keJ6JtTtQWAUI9Rb8U6zr0gvvAhzO-jukEYC1oq7ED941nEPoST3hPdnbOLV8O8mn96cQVO0GlCJv7RCmxzUqk5PVC04rdSO_9oH22yyLm_nFXHsunh_N2M2CuTcoMuxKVFVoVSzIGCct90G7bWsV7tiXHRNVdT3rcR8Xccv3Ijnq3Xsk8ejQ48lYSVoQCHzJ_YbLPROqN3Q_g";
        String token = keycloakTokenGateway.retrieveToken("chat-bot");
        System.out.println(token);
        AbstractXMPPConnection connection = xmppConnection.connection("chat-bot" , token);
        xmppClientListener.addListener(connection);
    }
}


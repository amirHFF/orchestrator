package io.projectZ.orchestrator.infrastructure.config;
/*
  Project : Authenticator
  Author  : AmirHFF
  Created : 5/22/2026 - 5:34 PM
*/

import io.github.amirHFF.exceptions.NotFoundException;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.KeycloakAdminClientTemp;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.KeycloakTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableScheduling
public class SecurityConfig {
    private final JwtAuthConverter converter;
    public static String API_TOKEN;

    @Lazy
    @Autowired
    private KeycloakAdminClientTemp keycloakAdminClientTemp;

    public SecurityConfig(JwtAuthConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable()).cors(cors -> {
                    cors.configurationSource(request -> {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
                        corsConfiguration.setMaxAge(1800L);
                        return corsConfiguration;
                    });
                })
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)));
        return httpSecurity.build();
    }

    @Scheduled(initialDelay = 10, fixedRate = 600, timeUnit = TimeUnit.SECONDS)
    public void updateApiToken() {
        System.out.println("api token updated");
        KeycloakTokenResponse tokenResponse = keycloakAdminClientTemp.generateApiToken();
        API_TOKEN = tokenResponse.getAccessToken();
    }

    public static String getCurrentToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken) {
            Jwt jwt = (Jwt) authentication;
            return jwt.getTokenValue();
        } else
            throw new RuntimeException("reqeust does not have access token");
    }
    public static Jwt getCurrentJWT() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken) {
            return (Jwt) authentication.getCredentials();
        } else
            throw new RuntimeException("reqeust does not have access token");
    }

    public static String getCurrentUsername() {
        Jwt jwt = getCurrentJWT();
        return jwt.getClaim("preferred_username");
    }
}


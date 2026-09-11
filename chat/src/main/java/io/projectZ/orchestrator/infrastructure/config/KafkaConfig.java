package io.projectZ.orchestrator.infrastructure.config;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/31/2026 - 2:03 AM
*/

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.util.backoff.FixedBackOff;

@EnableKafka
@Configuration
public class KafkaConfig {
    private final Logger logger= LogManager.getLogger(KafkaConfig.class);
    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler handler = new DefaultErrorHandler(
                (record, exception) -> {
                    logger.error("Skipping unprocessable record: {}", record, exception);
                },
                new FixedBackOff(1000L, 1L)
        );
        handler.addNotRetryableExceptions(DeserializationException.class);
        return handler;
    }
//    public static void main(String[] args){
//        String str ="{\"id\":\"9b3dfb25-1d7b-4981-a2b3-9e03996d0a21\",\"occurredAt\":1788128508677,\"realmId\":\"d41c8953-2445-4400-98c6-316ada88a426\",\"details\":{\"token_id\":\"trrtcc:a9ef56bf-de3b-a5ee-8209-5d3d8d6b2bf4\",\"grant_type\":\"client_credentials\",\"scope\":\"profile email\",\"client_auth_method\":\"client-secret\",\"username\":\"service-account-orchestrator-resource\"},\"userInfo\":{\"username\":\"service-account-orchestrator-resource\",\"email\":null},\"userid\":null,\"eventType\":\"CLIENT_LOGIN\",\"clientId\":\"orchestrator-resource\"}";
//        try {
//            UserEventDto  x= new ObjectMapper().readValue(str, UserEventDto.class);
//            System.out.println(x);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}


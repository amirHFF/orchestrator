package io.projectZ.orchestrator.persistence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/16/2026 - 2:12 AM
*/

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(
            @Value("${redis.host}") String host,
            @Value("${redis.port}") int port,
            @Value("${redis.password}") String password,
            @Value("${redis.database}") int database) {

        RedisStandaloneConfiguration config =
                new RedisStandaloneConfiguration(host, port);

        config.setDatabase(database);

//        if (password != null && !password.isBlank()) {
//            config.setPassword(RedisPassword.of(password));
//        }

        return new LettuceConnectionFactory(config);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(
            LettuceConnectionFactory connectionFactory) {

        return new StringRedisTemplate(connectionFactory);
    }
}

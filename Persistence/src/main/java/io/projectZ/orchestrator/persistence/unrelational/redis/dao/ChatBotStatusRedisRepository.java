package io.projectZ.orchestrator.persistence.unrelational.redis.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/15/2026 - 12:49 AM
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.projectZ.orchestrator.persistence.config.ObjectMapperFactory;
import io.projectZ.orchestrator.persistence.unrelational.redis.entity.ChatBotStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ChatBotStatusRedisRepository {

    private static final String KEY_PREFIX = "zchat:presence:";
    private final Logger logger = LogManager.getLogger();
    private static final Duration TTL = Duration.ofSeconds(3600000);
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper = ObjectMapperFactory.create();

    public ChatBotStatusRedisRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(ChatBotStatus status ) {

        try {

            String json =objectMapper.writeValueAsString(status);

            redisTemplate.opsForValue().set(buildKey(status.getBotId()),json,TTL);


        } catch (JsonProcessingException e) {

            throw new IllegalStateException("Could not serialize presence info",e);
        }
    }

    public List<ChatBotStatus> findAll(){
        List<ChatBotStatus> chatBotStatusList = new ArrayList<>();
        ScanOptions scanOptions = ScanOptions.scanOptions().match(KEY_PREFIX.concat("*")).build();
        Cursor<String> cursor =  redisTemplate.scan(scanOptions);
        List<String> redisKeyList = redisTemplate.opsForValue().multiGet(cursor.stream().toList());
        if (redisKeyList !=null) {
            chatBotStatusList = redisKeyList.stream().map(record -> {
                try {
                    return objectMapper.readValue(record, ChatBotStatus.class);
                } catch (JsonProcessingException e) {
                    logger.error("mapping redis returned json to object failed");
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        }
        return chatBotStatusList;
    }

    public Optional<ChatBotStatus> find(String botId) {

        String json =redisTemplate.opsForValue().get(buildKey(botId));

        if (json == null) {
            return Optional.empty();
        }

        try {

            return Optional.of(objectMapper.readValue(json, ChatBotStatus.class));

        } catch (JsonProcessingException e) {

            throw new IllegalStateException(
                    "Could not deserialize presence info",
                    e
            );
        }
    }

    public void delete(String botId) {

        redisTemplate.delete(buildKey(botId));
    }

    public void refresh(String botId) {

        redisTemplate.expire(buildKey(botId),TTL);
    }

    private String buildKey(String username) {

        return KEY_PREFIX + username;
    }
}

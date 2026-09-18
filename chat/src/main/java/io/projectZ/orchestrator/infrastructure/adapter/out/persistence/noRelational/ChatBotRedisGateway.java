package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/15/2026 - 7:10 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.ChatBotMapper;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.noRel.ChatBotCacheMapper;
import io.projectZ.orchestrator.persistence.unrelational.redis.dao.ChatBotStatusRedisRepository;
import io.projectZ.orchestrator.persistence.unrelational.redis.entity.ChatBotStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ChatBotRedisGateway implements ChatBotCacheGateway {

    private final ChatBotStatusRedisRepository cacheRepository;

    public ChatBotRedisGateway(ChatBotStatusRedisRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    @Override
    public void saveOrUpdate(ChatBotCacheDTO chatBotCacheDTO) {
        Optional<ChatBotStatus> optionalChatBotStatus = cacheRepository.find(chatBotCacheDTO.getBotId());
        if (optionalChatBotStatus.isPresent()) {
            ChatBotStatus loaded = optionalChatBotStatus.get();
            loaded.setEstablished(chatBotCacheDTO.isEstablished());
            loaded.setHasListener(chatBotCacheDTO.isHasListener());
            loaded.setLastUpdate(System.currentTimeMillis());
            cacheRepository.save(loaded);
        } else {
            cacheRepository.save(ChatBotCacheMapper.getInstance.modelToEntity(chatBotCacheDTO));
        }

    }

    @Override
    public void remove(String botId) {

        cacheRepository.delete(botId);
    }

    @Override
    public ChatBotCacheDTO get(String botId) {
        ChatBotStatus chatBotStatus = cacheRepository.find(botId).orElse(null);
        return ChatBotCacheMapper.getInstance.entityToModel(chatBotStatus);
    }

    @Override
    public List<ChatBotCacheDTO> list() {
        List<ChatBotStatus> chatBotStatusList = cacheRepository.findAll();
        return chatBotStatusList.stream().map(ChatBotCacheMapper.getInstance::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Boolean getBotHasListener(String botId) {
        ChatBotStatus chatBotStatus = cacheRepository.find(botId).orElse(null);
        if (chatBotStatus == null)
            return null;
        else
            return chatBotStatus.isHasListener();
    }

    @Override
    public Boolean getBotEstablishment(String botId) {
        ChatBotStatus chatBotStatus = cacheRepository.find(botId).orElse(null);
        if (chatBotStatus == null)
            return null;
        else
            return chatBotStatus.isEstablished();
    }

    @Override
    public void established(String botId) {
        Optional<ChatBotStatus> loadedBotStatus = cacheRepository.find(botId);
        if (loadedBotStatus.isEmpty()) {
            ChatBotStatus chatBotStatus = new ChatBotStatus(botId);
            chatBotStatus.setEstablished(true);
            cacheRepository.save(chatBotStatus);
        } else {
            loadedBotStatus.get().setEstablished(true);
            loadedBotStatus.get().setLastUpdate(System.currentTimeMillis());
            cacheRepository.save(loadedBotStatus.get());
        }
    }

    @Override
    public void unEstablished(String botId) {
        ChatBotStatus loadedBotStatus = cacheRepository.find(botId).orElse(null);
        if (loadedBotStatus !=null) {
            loadedBotStatus.setEstablished(false);
            loadedBotStatus.setLastUpdate(System.currentTimeMillis());
            cacheRepository.save(loadedBotStatus);
        }
    }

    @Override
    public void listenerAssigned(String botId) {
        Optional<ChatBotStatus> loadedBotStatus = cacheRepository.find(botId);
        if (loadedBotStatus.isEmpty()) {
            ChatBotStatus chatBotStatus = new ChatBotStatus(botId);
            chatBotStatus.setHasListener(true);
            cacheRepository.save(chatBotStatus);
        } else {
            loadedBotStatus.get().setHasListener(true);
            loadedBotStatus.get().setLastUpdate(System.currentTimeMillis());
            cacheRepository.save(loadedBotStatus.get());
        }
    }

    @Override
    public void listenerUnassigned(String botId) {
        ChatBotStatus loadedBotStatus = cacheRepository.find(botId).orElse(null);
        if (loadedBotStatus !=null) {
            loadedBotStatus.setHasListener(false);
            loadedBotStatus.setLastUpdate(System.currentTimeMillis());
            cacheRepository.save(loadedBotStatus);
        }
    }

}


package io.projectZ.orchestrator.infrastructure.adapter.in.web;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 10:35 AM
*/

import io.projectZ.orchestrator.application.service.ChatBotService;
import io.projectZ.orchestrator.entity.ChatBot;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.request.ChatBotRequestDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.response.ChatBotResponseDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.mapper.ChatBotControllerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
public class BotController {

    private final ChatBotService chatBotService;

    public BotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ChatBotResponseDto> createChatBot(@RequestBody ChatBotRequestDto chatBotRequestDto) {
        ChatBot chatBot = ChatBotControllerMapper.getInstance.requestToModel(chatBotRequestDto);
        ChatBot savedChatBot = chatBotService.save(chatBot);
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatBotControllerMapper.getInstance.ModelToResponse(chatBot));
    }
    @GetMapping(path = "/{botID}")
    public ResponseEntity<ChatBotResponseDto> getBot(@PathVariable String botID){
        ChatBot chatBot = chatBotService.get(botID);
        return ResponseEntity.ok(ChatBotControllerMapper.getInstance.ModelToResponse(chatBot));
    }

}


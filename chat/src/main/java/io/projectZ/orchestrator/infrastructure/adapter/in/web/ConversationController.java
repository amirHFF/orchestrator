package io.projectZ.orchestrator.infrastructure.adapter.in.web;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 7:02 PM
*/

import io.projectZ.orchestrator.application.service.ConversationService;
import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.request.ConversationRequest;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.response.ConversationResponse;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.mapper.ConversationControllerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(path = "/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{jid}")
    public ResponseEntity<List<ConversationResponse>> getAllConversation(@PathVariable String jid) {
        List<Conversation> responseSet = conversationService.getAllConversationsByUsername(jid);
        return ResponseEntity.ok().body(responseSet.stream().map(ConversationControllerMapper.getInstance::ModelToResponse).collect(Collectors.toList()));
    }
    @PutMapping
    public ResponseEntity<Boolean> save(@RequestBody ConversationRequest conversationRequest ) {
        conversationService.save(ConversationControllerMapper.getInstance.requestToModel(conversationRequest) );
        return ResponseEntity.status(201).body(true);
    }
}


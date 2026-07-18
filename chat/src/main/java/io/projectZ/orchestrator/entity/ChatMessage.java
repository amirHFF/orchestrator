package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 6:03 PM
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ChatMessage {

    @NotNull(message = "id is mandatory during work with chatMessage")
    private String id;
    @NotNull(message = "content is mandatory during work with chatMessage")
    private String content;
    @NotNull(message = "sendMessageTime is mandatory during work with chatMessage")
    private long messageTime;
    @NotNull(message = "from is mandatory during work with chatMessage")
    private String from;
    private String to;
    private Long groupId;

    public ChatMessage(String content, String from, String to) {
        id = UUID.randomUUID().toString();
        messageTime = System.currentTimeMillis();
        this.content = content;
        this.messageTime = messageTime;
        this.from = from;
        this.to = to;
    }

    public ChatMessage() {
        messageTime = System.currentTimeMillis();
        id = UUID.randomUUID().toString();
    }
}


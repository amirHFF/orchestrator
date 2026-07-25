package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 3:49 PM
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotUser extends UserBase{
    private String password;

    public BotUser() {
        setEmail("project-z.chat-bot@gmail.com");
    }
}


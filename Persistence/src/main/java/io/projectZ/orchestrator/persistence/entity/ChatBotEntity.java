package io.projectZ.orchestrator.persistence.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 5:55 PM
*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "CHAT_BOT")
@Entity
public class ChatBotEntity extends BaseEntity{
    @Id
    @SequenceGenerator(name = "chatBotSeq" , sequenceName = "CHAT_BOT_SEQ" , allocationSize = 1)
    @GeneratedValue(generator = "chatBotSeq" , strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "KEYCLOAK_ID" , unique = true , nullable = false )
    private String keycloakId;
    @Column(name = "BOT_ID" ,unique = true , nullable = false)
    private String botID;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ai_model")
    private String aiModel;
    @Column(name = "PROMPT_CODE")
    private String promptCode;
    @Column(name = "OWNER_USER_ID")
    private String ownerUserId;
    private boolean enabled;
    private String scope;
}


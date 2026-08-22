package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 1:04 AM
*/

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChatProfile {

    private Long id;
    private String keycloakId;
    private String displayName;
    private String username;
    private String firstName;
    private String lastname;
    private LocalDate birthDate;
}


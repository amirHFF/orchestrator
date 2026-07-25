package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 2:29 PM
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBase {
    private String id ;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
}


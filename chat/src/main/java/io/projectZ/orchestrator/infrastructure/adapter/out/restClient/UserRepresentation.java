package io.projectZ.orchestrator.infrastructure.adapter.out.restClient;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/10/2026 - 11:26 PM
*/


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserRepresentation {

  private String id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private Boolean emailVerified;
  private Boolean enabled;
  private Long createdTimestamp;
  private Boolean totp;
  private List<String> requiredActions;
  private Map<String, List<String>> attributes;

}


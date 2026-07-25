package io.projectZ.orchestrator.dto;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/11/2026 - 2:21 AM
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeycloakTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("scope")
    private String scope;
}


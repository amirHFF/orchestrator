package io.projectZ.orchestrator.dto;
/*
  Project : HealthCareService
  Author  : AmirHFF
  Created : 6/1/2026 - 1:23 AM
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credential {
    private String type;
    private String value;
    private boolean temporary;
}


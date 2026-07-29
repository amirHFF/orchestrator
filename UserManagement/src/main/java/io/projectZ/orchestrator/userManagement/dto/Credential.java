package io.projectZ.orchestrator.userManagement.dto;
/*
  Project : HealthCareService
  Author  : AmirHFF
  Created : 6/1/2026 - 1:23 AM
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    private String type;
    private String value;
    private boolean temporary;
}


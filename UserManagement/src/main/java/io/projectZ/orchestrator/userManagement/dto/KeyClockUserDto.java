package io.projectZ.orchestrator.userManagement.dto;
/*
  Project : HealthCareService
  Author  : AmirHFF
  Created : 6/1/2026 - 1:21 AM
*/

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyClockUserDto {

    private String username;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
    private List<Credential> credentials;

}


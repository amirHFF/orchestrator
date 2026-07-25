package io.projectZ.orchestrator.application.port;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 11:42 AM
*/

import io.projectZ.orchestrator.entity.UserBase;

public interface UserManagementPort {
    String registerUser(UserBase userBase);
}


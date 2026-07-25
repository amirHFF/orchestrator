package io.projectZ.orchestrator.controller.mapper;
/*
  Project : HealthCareService
  Author  : AmirHFF
  Created : 5/29/2026 - 10:39 AM
*/

public interface BaseControllerMapper<M ,RES , REQ >{
    M requestToModel(REQ request);

    RES ModelToResponse(M dto);

}


package io.projectZ.orchestrator.ai.DBRepository.mapper;
/*
  Project : HealthCareService
  Author  : AmirHFF
  Created : 5/27/2026 - 6:59 PM
*/

public interface BaseMapper<E, M> {

    E modelToEntity(M model);

    M entityToModel(E entity);

}




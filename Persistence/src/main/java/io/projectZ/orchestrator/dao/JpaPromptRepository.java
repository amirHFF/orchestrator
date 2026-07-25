package io.projectZ.orchestrator.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 9:45 AM
*/

import io.projectZ.orchestrator.entity.PromptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JpaPromptRepository extends JpaRepository<PromptEntity , Long> {
    PromptEntity findByCode(String code);
}


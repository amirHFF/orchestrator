package io.projectZ.orchestrator.persistence.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 9:45 AM
*/

import io.projectZ.orchestrator.persistence.entity.PromptEntity;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPromptRepository extends JpaRepository<PromptEntity , Long> {
    PromptEntity findByCode(String code);

    List<PromptEntity> findAllByParentPromptCodeOrCode(String parentPromptCode , String code);
}


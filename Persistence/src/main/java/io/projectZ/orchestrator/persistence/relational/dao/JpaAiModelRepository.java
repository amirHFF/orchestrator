package io.projectZ.orchestrator.persistence.relational.dao;

import io.projectZ.orchestrator.persistence.relational.entity.AIModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAiModelRepository extends JpaRepository<AIModelEntity, Long> {
	AIModelEntity findByName(String name);
}

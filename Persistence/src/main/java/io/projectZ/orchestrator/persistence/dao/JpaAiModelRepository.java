package io.projectZ.orchestrator.persistence.dao;

import io.projectZ.orchestrator.persistence.entity.AIModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAiModelRepository extends JpaRepository<AIModelEntity , Long> {
	AIModelEntity findByName(String name);
}

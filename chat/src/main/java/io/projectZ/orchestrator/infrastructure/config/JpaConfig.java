package io.projectZ.orchestrator.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.dao")
@EntityScan(basePackages = "io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.model")
public class JpaConfig {


}

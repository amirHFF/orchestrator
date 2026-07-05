package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
  Project : HealthCareService
  Author  : AmirHFF
  Created : 5/27/2026 - 8:10 AM
*/
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {


    private long version;
    @Column(name = "INSERT_TIME")
    private LocalDateTime insertTime;
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;


    @PrePersist
    public void prePersist() {
        version = 0;
        insertTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        version++;
        updateTime = LocalDateTime.now();

    }
}


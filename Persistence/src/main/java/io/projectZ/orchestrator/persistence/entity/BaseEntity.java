package io.projectZ.orchestrator.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.ThreadContext;

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
    @Column(name = "INSERT_USER_ID")
    private String insertUserId;
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;
    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;


    @PrePersist
    public void prePersist() {
        version = 0;
        insertTime = LocalDateTime.now();
        insertUserId = ThreadContext.get("current-userId");
    }

    @PreUpdate
    public void preUpdate() {
        version++;
        updateTime = LocalDateTime.now();
        updateUserId = ThreadContext.get("current-userId");

    }
}


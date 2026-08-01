package io.projectZ.orchestrator.persistence.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 10:23 PM
*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PROMPT")
public class PromptEntity extends BaseEntity{
    @Id
    @SequenceGenerator(name = "promptSeq" , sequenceName = "PROMPT_SEQ" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "promptSeq")
    private long id;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    @Column(nullable = false ,unique = true)
    private String code;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private PromptTypeEnum promptType;

    private Integer priority;

}


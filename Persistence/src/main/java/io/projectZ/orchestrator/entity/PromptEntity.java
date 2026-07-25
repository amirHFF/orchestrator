package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 10:23 PM
*/

import jakarta.persistence.*;

@Entity
@Table
public class PromptEntity extends BaseEntity{
    @Id
    @SequenceGenerator(name = "promptSeq" , sequenceName = "PROMPT_SEQ" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "promptSeq")
    private long id;

    @Column(nullable = false)
    private String prompt;
    @Column(nullable = false ,unique = true)
    private String code;
    private String title;

    @Column(name = "PARENT_PROMPT_CODE")
    private String parentPromptCode;
    @Enumerated(value = EnumType.STRING)
    private PromptTypeEnum promptType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentPromptCode() {
        return parentPromptCode;
    }

    public void setParentPromptCode(String parentPromptCode) {
        this.parentPromptCode = parentPromptCode;
    }
}


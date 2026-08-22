package io.projectZ.orchestrator.persistence.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/19/2026 - 11:42 PM
*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
public class ChatProfileEntity {

    @Id
    @SequenceGenerator(name = "userProfileSeq" , sequenceName = "USER_PROFILE_SEQ" ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "userProfileSeq")
    private long id;
    private String keycloakId;
    private String displayName;
    private String username;
    private String firstName;
    private String lastname;
    private LocalDate birthDate;

}


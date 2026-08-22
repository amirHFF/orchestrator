package io.projectZ.orchestrator.persistence.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:36 PM
*/

 import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

 import java.util.HashSet;
 import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "conversation")
public class ConversationEntity extends BaseEntity {
    @Id
    @SequenceGenerator(name = "conversationSeq" , sequenceName = "CONVERSATION_SEQ" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "conversationSeq")
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "conversation_participant",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {
                    @UniqueConstraint(
                            name = "uk_conversation_participant",
                            columnNames = {"conversation_id", "user_id"}
                    )
            }
    )
    private Set<ChatProfileEntity> participants = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private ConversationTypeEnum conversationType;

    @Column(name = "LAST_MESSAGE" )
    private String lastMessage;

}


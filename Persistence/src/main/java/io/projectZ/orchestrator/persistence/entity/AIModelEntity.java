package io.projectZ.orchestrator.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "AI_MODEL")
@Entity
public class AIModelEntity extends BaseEntity {

	@Id
	@SequenceGenerator(name = "AiModelSeq", sequenceName = "AI_MODEL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AiModelSeq")
	private long id;
	@Column(unique = true, nullable = false)
	private String name;

	private String apiKey;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String url;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AiModelType type;

	private Long usedToken;

	private boolean isActive;

	private Double temperature;

}

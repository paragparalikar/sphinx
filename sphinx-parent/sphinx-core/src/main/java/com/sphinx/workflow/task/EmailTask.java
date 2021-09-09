package com.sphinx.workflow.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sphinx.common.enums.RecipientType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class EmailTask extends Task {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message="Recipient for email is required")
	private RecipientType to;
	
	@Size(min = 3, max = 255)
	private String applicationId;
	
	@Size(min = 3, max = 255)
	@Column(nullable = false)
	@NotBlank(message = "Subject template can not be blank")
	private String subjectTemplate;
	
	@Lob
	@Size(min = 3, max = 2550)
	@Column(nullable = false)
	@NotBlank(message = "Content template can not be blank")
	private String contentTemplate;
	
}

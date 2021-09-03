package com.sphinx.workflow.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class TransformerTask extends Task {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	@NotBlank
	@Column(nullable = false)
	private String script;
	
}

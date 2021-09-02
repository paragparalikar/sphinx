package com.sphinx.workflow.model.vertex;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sphinx.workflow.model.RecipientType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmailVertex extends Vertex {
	
	@NotNull(message="Recipient for email is required")
	private RecipientType to;
	
	@Size(min = 3, max = 255)
	private String applicationId;
	
	@Size(min = 3, max = 255)
	@NotBlank(message = "Subject template can not be blank")
	private String subjectTemplate;
	
	@Size(min = 3, max = 2550)
	@NotBlank(message = "Content template can not be blank")
	private String contentTemplate;
	
}

package com.sphinx.web.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sphinx.web.workflow.WorkflowDTO;

import lombok.Data;

@Data
public class FormDTO {

	private Long id;
	
	@NotBlank 
	@Size(min = 3, max = 255) 
	private String name;
	
	@NotNull
	private WorkflowDTO workflow;
}

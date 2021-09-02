package com.sphinx.web.workflow;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class WorkflowDTO {

	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String name;
	
	
}

package com.sphinx.web.workflow;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sphinx.common.interfaces.NamedModel;

import lombok.Data;

@Data
public class WorkflowDTO implements NamedModel {
	private static final long serialVersionUID = 4717050829036616760L;

	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String name;
	
	
}

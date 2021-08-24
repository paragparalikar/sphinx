package com.sphinx.web.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FormDTO {

	private Long id;
	
	@NotBlank 
	@Size(min = 3, max = 255) 
	private String name;
	
}

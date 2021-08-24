package com.sphinx.web.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper = true)
public class FormDetailsDTO extends FormDTO {

	@NotBlank
	private String components; 
	 
}

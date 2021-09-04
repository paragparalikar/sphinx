package com.sphinx.web.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestDetailsDTO extends RequestDTO {
	
	@NotBlank
	private String payload;

}

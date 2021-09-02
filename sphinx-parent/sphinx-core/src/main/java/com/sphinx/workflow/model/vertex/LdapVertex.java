package com.sphinx.workflow.model.vertex;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LdapVertex extends Vertex {

	@NotBlank private String url;
	@NotBlank private String username;
	@NotBlank private String password;
	
}

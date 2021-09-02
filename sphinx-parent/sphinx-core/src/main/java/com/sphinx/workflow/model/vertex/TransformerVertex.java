package com.sphinx.workflow.model.vertex;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransformerVertex extends Vertex {

	@NotBlank
	private String script;
	
}

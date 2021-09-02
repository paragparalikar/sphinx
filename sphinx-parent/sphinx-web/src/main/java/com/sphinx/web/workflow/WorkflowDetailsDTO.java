package com.sphinx.web.workflow;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.sphinx.workflow.model.Node;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowDetailsDTO extends WorkflowDTO {

	@NotEmpty
	private Map<Integer,@Valid Node> data;
	
}

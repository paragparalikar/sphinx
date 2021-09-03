package com.sphinx.web.workflow;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.sphinx.workflow.WorkflowConstraint;
import com.sphinx.workflow.model.Node;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowDetailsDTO extends WorkflowDTO {

	@NotEmpty
	@WorkflowConstraint
	private Map<String,@Valid Node> data;
	
}

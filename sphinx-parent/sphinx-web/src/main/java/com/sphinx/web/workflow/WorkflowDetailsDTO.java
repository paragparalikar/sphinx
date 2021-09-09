package com.sphinx.web.workflow;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.sphinx.workflow.node.Node;
import com.sphinx.workflow.validation.WorkflowConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowDetailsDTO extends WorkflowDTO {
	private static final long serialVersionUID = 5676731655377661839L;

	@NotEmpty
	@WorkflowConstraint
	private Map<String,@Valid Node> data;
	
}

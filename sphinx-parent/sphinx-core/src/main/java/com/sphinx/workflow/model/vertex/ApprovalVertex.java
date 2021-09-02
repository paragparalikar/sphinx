package com.sphinx.workflow.model.vertex;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sphinx.workflow.model.ApproverType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApprovalVertex extends Vertex {

	@NotNull(message="Approver is required")
	private ApproverType approver;
	
	@Size(min = 3, max = 255)
	private String applicationId;
	
}

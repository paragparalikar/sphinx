package com.sphinx.workflow.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sphinx.workflow.node.ApproverType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ApprovalTask extends Task {
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message="Approver is required")
	private ApproverType approver;
	
	@Size(min = 3, max = 255)
	private String applicationId;
	
}

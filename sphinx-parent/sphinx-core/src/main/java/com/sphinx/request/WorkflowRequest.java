package com.sphinx.request;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Immutable;

import com.sphinx.common.interfaces.NamedModel;
import com.sphinx.workflow.Workflow;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Immutable
@NoArgsConstructor
@DiscriminatorValue("WORKFLOW")
@EqualsAndHashCode(callSuper = true)
public class WorkflowRequest extends Request {
	private static final long serialVersionUID = -8636978231439701636L;

	@ManyToOne
	private Workflow workflow;
	
	@Override
	public NamedModel getTarget() {
		return workflow;
	}
	
}

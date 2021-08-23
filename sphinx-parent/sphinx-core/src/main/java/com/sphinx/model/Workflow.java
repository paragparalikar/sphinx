package com.sphinx.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Workflow extends AbstractTasklet {
	private static final long serialVersionUID = -5954903782225961806L;
	
	public Workflow(String id, String name) {
		super(id, name);
	}

}

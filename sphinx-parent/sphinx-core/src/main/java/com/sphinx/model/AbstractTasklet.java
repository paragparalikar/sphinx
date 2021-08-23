package com.sphinx.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@RequiredArgsConstructor
public abstract class AbstractTasklet implements Tasklet {
	private static final long serialVersionUID = -1823129254194556697L;

	private final String id;
	private final String name;
	
	
}

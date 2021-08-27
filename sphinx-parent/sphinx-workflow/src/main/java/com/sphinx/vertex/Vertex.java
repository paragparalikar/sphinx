package com.sphinx.vertex;

import java.io.Serializable;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Vertex<T> implements Serializable {
	private static final long serialVersionUID = 7974680867283273025L;

	@NonNull private final T payload;
	@NonNull private final String type;
	
}

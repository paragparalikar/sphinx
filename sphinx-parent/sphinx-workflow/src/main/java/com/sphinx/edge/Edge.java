package com.sphinx.edge;

import java.io.Serializable;

import com.sphinx.vertex.Vertex;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Edge implements Serializable {
	private static final long serialVersionUID = 153979976894460118L;

	@NonNull private final Vertex from, to;
	
}

package com.sphinx.graph;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.sphinx.edge.Edge;
import com.sphinx.vertex.Vertex;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Graph implements Serializable {
	private static final long serialVersionUID = 4362939827202237513L;
	
	@Builder.Default
	private final Set<Edge> edges = new HashSet<>();

	@Builder.Default
	private final Set<Vertex> vertices = new HashSet<>();
	
	
	
}

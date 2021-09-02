package com.sphinx.workflow.model;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sphinx.workflow.model.vertex.Vertex;

import lombok.Data;

@Data
public class Node {

	private int id;
	private int pos_x;
	private int pos_y;
	private String name; 
	private String html;
	private boolean typenode;
	
	@JsonProperty("class") 
	private String clazz;
	private Map<String, Input> inputs = new HashMap<>();
	private Map<String, Output> outputs = new HashMap<>();
	private Map<String, @Valid Vertex> data = new HashMap<>();
	
}

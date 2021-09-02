package com.sphinx.workflow.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Output {

	private List<OutputConnection> connections = new ArrayList<>(); 
	
}

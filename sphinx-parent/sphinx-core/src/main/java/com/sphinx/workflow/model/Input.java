package com.sphinx.workflow.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Input {

	private List<InputConnection> connections = new ArrayList<>();
	
}

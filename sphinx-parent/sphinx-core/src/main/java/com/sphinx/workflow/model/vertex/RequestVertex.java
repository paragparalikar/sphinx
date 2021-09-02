package com.sphinx.workflow.model.vertex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequestVertex extends Vertex {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
}

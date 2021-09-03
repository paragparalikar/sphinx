package com.sphinx.workflow.task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequestTask extends Task {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
}

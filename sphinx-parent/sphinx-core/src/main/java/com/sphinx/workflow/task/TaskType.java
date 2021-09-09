package com.sphinx.workflow.task;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskType {

	@JsonProperty("request")
	REQUEST, 
	
	@JsonProperty("email")
	EMAIL,
	
	@JsonProperty("approval")
	APPROVAL, 
	
	@JsonProperty("transformer")
	TRANSFORMER, 
	
	@JsonProperty("ldap")
	LDAP,
	
	@JsonProperty("workflow")
	WORKFLOW;
	
}

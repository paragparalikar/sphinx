package com.sphinx.workflow.model.vertex;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "type")
@JsonSubTypes({ 
  @Type(value = RequestVertex.class, name = "request"), 
  @Type(value = EmailVertex.class, name = "email"), 
  @Type(value = ApprovalVertex.class, name = "approval"),
  @Type(value = TransformerVertex.class, name = "transformer"),
  @Type(value = LdapVertex.class, name = "ldap")
})
public abstract class Vertex {

	private String type;
	
}

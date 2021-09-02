package com.sphinx.workflow.model.vertex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vertex {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}

package com.sphinx.workflow.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  include = JsonTypeInfo.As.EXISTING_PROPERTY, 
  property = "type",
  visible = true)
@JsonSubTypes({ 
  @Type(value = RequestTask.class, name = "request"), 
  @Type(value = EmailTask.class, name = "email"), 
  @Type(value = ApprovalTask.class, name = "approval"),
  @Type(value = TransformerTask.class, name = "transformer"),
  @Type(value = LdapTask.class, name = "ldap"),
  @Type(value = WorkflowTask.class, name = "workflow")
})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Task {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskType type;
}

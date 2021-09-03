package com.sphinx.workflow.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class LdapTask extends Task {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank 
	@Column(nullable = false)
	private String url;
	
	@NotBlank 
	@Column(nullable = false)
	private String username;
	
	@NotBlank 
	@Column(nullable = false)
	private String password;
	
}

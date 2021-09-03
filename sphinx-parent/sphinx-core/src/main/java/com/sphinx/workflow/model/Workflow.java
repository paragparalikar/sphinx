package com.sphinx.workflow.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sphinx.workflow.validation.WorkflowConstraint;

import lombok.Data;

@Data
@Entity
public class Workflow implements Serializable {
	private static final long serialVersionUID = -760095821715213633L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank 
	@Size(min = 3, max = 255) 
	@Column(nullable = false, unique = true)
	private String name;
	
	@NotEmpty 
	@WorkflowConstraint
	@MapKey(name = "id")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Map<@NotNull String, @Valid Node> data;
}

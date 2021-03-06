package com.sphinx.workflow.node;

import java.util.HashMap;
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
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sphinx.workflow.task.Task;

import lombok.Data;

@Data
@Entity
public class Node {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;
	
	private String id;
	private int pos_x;
	private int pos_y;
	private boolean typenode;
	
	@NotBlank
	@Column(nullable = false)
	private String name; 
	
	@NotBlank
	@Column(nullable = false)
	private String html;
	
	@Valid 
	@NotNull 
	@OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
	private Task data;
	
	@JsonProperty("class") 
	private String clazz;
	
	@MapKey(name = "name")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Map<@NotBlank String, @Valid Input> inputs = new HashMap<>();
	
	@MapKey(name = "name")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Map<@NotBlank String, @Valid Output> outputs = new HashMap<>();
	
}

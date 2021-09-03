package com.sphinx.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sphinx.workflow.Workflow;

import lombok.Data;

@Data
@Entity
public class Form implements Serializable {
	private static final long serialVersionUID = 2737151418955494334L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank 
	@Size(min = 3, max = 255) 
	@Column(nullable = false, unique = true)
	private String name;
	
	@NotNull
	@ManyToOne(optional = false)
	private Workflow workflow;
	
	@Lob
	@NotBlank 
	private String components; 

}

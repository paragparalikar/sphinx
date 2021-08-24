package com.sphinx.form;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Form implements Serializable {
	private static final long serialVersionUID = 2737151418955494334L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Version 
	private Long version;
	
	@NotBlank 
	@Size(min = 3, max = 255) 
	private String name;
	
	@Lob
	private String components; 

}

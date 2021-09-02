package com.sphinx.workflow.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
@Entity
public class Workflow implements Serializable {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final long serialVersionUID = -760095821715213633L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank 
	@Size(min = 3, max = 255) 
	private String name;
	
	@NotEmpty
	@Transient
	private Map<Integer,@Valid Node> data;
	
	@Lob
	private String payload;
	
	@PreUpdate
	@PrePersist
	public void prePersist() throws JsonProcessingException {
		this.payload = objectMapper.writeValueAsString(data);
	}
	
	@PostLoad
	public void postLoad() throws JsonMappingException, JsonProcessingException {
		this.data = objectMapper.readValue(this.payload, new TypeReference<Map<Integer,Node>>() {});
	}
}

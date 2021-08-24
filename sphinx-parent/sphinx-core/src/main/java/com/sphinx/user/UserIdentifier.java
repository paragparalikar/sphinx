package com.sphinx.user;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class UserIdentifier {

	private String system;
	
	private String identifier;
}

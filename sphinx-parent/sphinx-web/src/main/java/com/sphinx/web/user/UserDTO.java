package com.sphinx.web.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	
}

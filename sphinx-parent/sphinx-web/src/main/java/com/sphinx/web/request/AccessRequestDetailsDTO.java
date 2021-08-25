package com.sphinx.web.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sphinx.web.user.UserDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccessRequestDetailsDTO extends AccessRequestDTO {
	
	@NotBlank
	private String payload;
	
	@NotEmpty
	private List<@Valid UserDTO> users;

}

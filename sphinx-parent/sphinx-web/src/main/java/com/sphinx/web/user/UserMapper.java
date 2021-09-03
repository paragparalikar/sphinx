package com.sphinx.web.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sphinx.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO entityToDTO(User user);
	
	@Mapping(target = "identifier", ignore=true)
	@Mapping(target = "identifiers", ignore=true)
	User dtoToEntity(UserDTO dto);
	
	List<UserDTO> entityToDTOs(List<User> user);
	
}

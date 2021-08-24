package com.sphinx.web.user;

import java.util.List;

import org.mapstruct.Mapper;

import com.sphinx.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO entityToDTO(User user);
	
	User dtoToEntity(UserDTO dto);
	
	List<UserDTO> entityToDTOs(List<User> user);
	
}

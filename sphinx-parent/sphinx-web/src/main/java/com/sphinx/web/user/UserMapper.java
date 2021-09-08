package com.sphinx.web.user;

import org.mapstruct.Mapper;

import com.sphinx.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO entityToDTO(User user);
	
}

package com.sphinx.web.user;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sphinx.user.User;
import com.sphinx.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

	private final UserMapper userMapper;
	private final UserService userService;
	
	
	@GetMapping(params = "q")
	public List<UserDTO> findSuggestions(@RequestParam("q") String query){
		final List<User> users = userService.findSuggestions(query);
		return userMapper.entityToDTOs(users);
	}
	
}

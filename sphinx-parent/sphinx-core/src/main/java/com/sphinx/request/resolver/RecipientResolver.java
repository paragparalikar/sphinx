package com.sphinx.request.resolver;

import org.springframework.stereotype.Component;

import com.sphinx.common.enums.RecipientType;
import com.sphinx.request.Request;
import com.sphinx.user.User;
import com.sphinx.user.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecipientResolver {

	private final UserService userService;

	public User resolve(Request request, RecipientType type, String applicationId) {
		switch (type) {
		case APPLICATION_OWNER:
			return userService.getOwnerByApplicationId(applicationId);
		case REQUESTER:
			return userService.loadUserByUsername(request.getCreatedBy());
		case MANAGER1:
			final User user = userService.loadUserByUsername(request.getCreatedBy());
			return userService.loadUserByUsername(user.getManager());
		case MANAGER2:
			final User user1 = userService.loadUserByUsername(request.getCreatedBy());
			final User manager1 = userService.loadUserByUsername(user1.getManager());
			return userService.loadUserByUsername(manager1.getManager());
		default:
			throw new IllegalArgumentException(
					String.format("No recipient found for request id %d with approver type %s and application id %s", 
							request.getId(), type, applicationId));
		}
	}

}

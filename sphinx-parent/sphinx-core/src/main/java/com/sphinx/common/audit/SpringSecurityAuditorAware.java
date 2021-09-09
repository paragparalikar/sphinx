package com.sphinx.common.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sphinx.user.User;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
					.map(SecurityContext::getAuthentication)
					.map(Authentication::getPrincipal)
					.map(User.class::cast)
					.map(User::getUsername);
	}

}

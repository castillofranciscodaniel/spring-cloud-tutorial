package com.springboot.app.oauth.events;

import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

		String mensaje = String.format("Success Login: %s", userPrincipal.getUsername());
		System.out.println(mensaje);
		log.info(mensaje);
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = String.format("Error Login: %s", exception.getMessage());
		System.out.println(mensaje);
		log.error(mensaje);
	}

}

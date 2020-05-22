package com.springboot.app.oauth.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.app.commons.user.models.User;
import com.springboot.app.oauth.service.IUserService;

import feign.FeignException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private IUserService userService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		String username = userPrincipal.getUsername();
		
		User user = this.userService.findByUsername(username);
		user.resetLoginTry();
				
		this.userService.update(user);
		
		String mensaje = String.format("Success Login: %s", userPrincipal.getUsername());
		System.out.println(mensaje);
		log.info(mensaje);
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String username = authentication.getName();

		try {
			User user = this.userService.findByUsername(username);
			user.addLoginTry();
			
			if (!user.getEnabled()) {
				log.error("Usuario {} bloqueado por máximos intentos de login permitidos. Intentos totales: {}.",
						username, user.getLoginTry());
			}
			
			this.userService.update(user);
		} catch (FeignException e) {
			log.error("Usuario no existe: {}", username);
		}

		String mensaje = String.format("Error Login: %s", exception.getMessage());
		System.out.println(mensaje);
		log.error(mensaje);
	}

}

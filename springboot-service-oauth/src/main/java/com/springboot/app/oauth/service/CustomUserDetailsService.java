package com.springboot.app.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.app.commons.user.models.User;
import com.springboot.app.oauth.clients.UserClientRest;
import com.springboot.app.oauth.models.UserPrincipal;

import feign.FeignException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService, IUserService {

	@Autowired
	private UserClientRest userClientRest;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = this.userClientRest.findByUsernameLike("ByUsernameLike", username);
			if(user == null) {
				log.error("Error de login, no existe usuario: {}", username);
				throw new UsernameNotFoundException(String.format("Error de login, no existe usuario: %s", username));
			} else {

				log.info("User Autenticated: {}", username);
				return UserPrincipal.create(user);
			}
		} catch (FeignException e) {
			log.error("Error de login, no existe usuario: {}", username);
			throw new UsernameNotFoundException(String.format("Error de login, no existe usuario: %s", username));
		}
	}

	@Override
	public User findByUsername(String username) {
		return this.userClientRest.findByUsernameLike("ByUsernameLike", username);
	}

	@Override
	public User update(User user) {
		return this.userClientRest.update(user);
	}

}

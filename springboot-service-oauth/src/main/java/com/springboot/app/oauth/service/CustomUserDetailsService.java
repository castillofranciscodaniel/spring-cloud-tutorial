package com.springboot.app.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.app.commons.user.models.User;
import com.springboot.app.oauth.clients.UserClientRest;
import com.springboot.app.oauth.models.UserPrincipal;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserClientRest userClientRest;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userClientRest.findByUsernameLike("ByUsernameLike", username);

		if (user == null) {
			log.error("Error de login, no existe usuario: {}", username);
			throw new UsernameNotFoundException(String.format("Error de login, no existe usuario: %s", username));
		}

//		List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName()))
//				.peek(authoriti -> log.info("Role: {}", authoriti.getAuthority())).collect(Collectors.toList());

		log.info("User Autenticated: {}", username);

//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				user.getEnabled(), true, true, true, authorities);
		return UserPrincipal.create(user);
	}

	// This method is used by JWTAuthenticationFilter
//	@Transactional
//	public UserDetails loadUserById(Long id) {
//		User user = this.userClientRest.findById(id);
//		return UserPrincipal.create(user);
//	}

}

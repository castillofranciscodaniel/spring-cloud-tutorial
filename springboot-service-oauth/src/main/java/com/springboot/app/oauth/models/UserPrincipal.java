package com.springboot.app.oauth.models;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.app.commons.user.models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = -8689724464675897932L;

	private Long id;

	private String username;

	private String password;

	private String mail;

	private Integer dni;

	private String name;

	private String lastName;

	private Boolean enabled;
	
	private Integer loginTry;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String username, String password, String mail, Integer dni, String name,
			String lastName, Boolean enabled, Integer loginTry, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.enabled = enabled;
		this.loginTry = loginTry;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getMail(), user.getDni(),
				user.getName(), user.getLastName(), user.getEnabled(), user.getLoginTry(), authorities);
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { 
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}

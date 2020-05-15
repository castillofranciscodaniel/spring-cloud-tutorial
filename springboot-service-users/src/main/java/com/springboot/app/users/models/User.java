package com.springboot.app.users.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.springboot.app.commons.models.util.DateAudit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User extends DateAudit implements Serializable {

	private static final long serialVersionUID = -1931625435127346213L;

	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	@Column(unique = true, length = 100)
	private String email;
	
	private String name;
	
	private String lastName;
	
	private Boolean enabled;

//	@ManyToMany
//	// @JoinTable(uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
//	// "roles_id" }))
//	private Set<Role> roles;
//
//	@OneToOne
//	private StateUser state;

}

package com.springboot.app.commons.user.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.springboot.app.commons.audit.entity.models.DateAudit;

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
	private String mail;
	
	private Integer dni;

	private String name;
	
	private String lastName;
	
	private Boolean enabled;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), 
	uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "role_id" }))
	private Set<Role> roles;

	@OneToOne
	private StateUser state;


}

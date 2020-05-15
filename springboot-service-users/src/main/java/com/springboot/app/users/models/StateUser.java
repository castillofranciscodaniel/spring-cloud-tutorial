package com.springboot.app.users.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "states_user")
public class StateUser implements Serializable {

	private static final long serialVersionUID = -3748157574756140674L;

	@Column(unique = true, length = 20)
	private String name;

}

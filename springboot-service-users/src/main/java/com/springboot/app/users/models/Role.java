package com.springboot.app.users.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "rols")
public class Role implements Serializable {

	private static final long serialVersionUID = -5840653378609792375L;

	@Column(unique = true, length = 20)
	private String name;
}

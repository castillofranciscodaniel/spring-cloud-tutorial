package com.springboot.app.commons.user.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends Model implements Serializable {

	private static final long serialVersionUID = -5840653378609792375L;

	@Column(unique = true, length = 20)
	private String name;
}

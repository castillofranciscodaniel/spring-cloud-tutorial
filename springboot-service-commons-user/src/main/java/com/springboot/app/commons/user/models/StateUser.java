package com.springboot.app.commons.user.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.springboot.app.commons.audit.entity.models.ModelSql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "states_user")
public class StateUser extends ModelSql<Long> implements Serializable
{

	private static final long serialVersionUID = -3748157574756140674L;

	@Column(unique = true, length = 20)
	private String name;

}

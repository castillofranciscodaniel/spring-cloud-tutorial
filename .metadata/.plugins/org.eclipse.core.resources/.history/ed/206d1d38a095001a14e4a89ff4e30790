package com.springboot.app.commons.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springboot.app.commons.models.util.DateAudit;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends DateAudit implements Serializable {

	private static final long serialVersionUID = -3568706430523675600L;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	private Double price;

	@Transient
	private Integer port;

	// Los constructores fueron creados para el ejemplo de la query HDL... si no
	// usamos.. quizas no hagan falta
	public Product() {
	}

	public Product(Long id, @NotNull @NotBlank String name, @NotNull Double price) {
		super(id);
		this.name = name;
		this.price = price;
	}

}

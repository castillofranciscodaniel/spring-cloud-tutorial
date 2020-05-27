package com.springboot.app.items.models;

import java.io.Serializable;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product  implements Serializable {

	private static final long serialVersionUID = -3568706430523675600L;
	
	private Long id;
	
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
		this.id = id;
		this.name = name;
		this.price = price;
	}

}

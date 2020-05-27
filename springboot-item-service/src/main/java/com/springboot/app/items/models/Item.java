package com.springboot.app.items.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

	private Product product;

	private Integer amount;

	public Item() {
	}

	public Item(Product product, Integer amount) {
		
		super();
		this.product = product;
		this.amount = amount;
	}

	public Double getTotal() {
		return this.getAmount() * this.getProduct().getPrice();
	}

}

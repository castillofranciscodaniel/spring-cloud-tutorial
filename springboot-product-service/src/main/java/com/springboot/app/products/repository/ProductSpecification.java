package com.springboot.app.products.repository;

import org.springframework.data.jpa.domain.Specification;

import com.springboot.app.products.models.Product;


public class ProductSpecification {

	public static Specification<Product> hashName(String name) {
		return (product, cq, cb) -> cb.equal(product.get("name"), name);
	}

}

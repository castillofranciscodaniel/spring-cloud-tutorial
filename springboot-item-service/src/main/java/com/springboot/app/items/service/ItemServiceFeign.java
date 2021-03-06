package com.springboot.app.items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.app.items.clients.ProductClientRest;
import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.Product;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class ItemServiceFeign implements ItemService {

	@NonNull
	private final ProductClientRest productClientRest;

	@Override
	public List<Item> findAll() {
		try {
			return this.productClientRest.list().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Item findByProductId(Long productId) {
		try {
			Product product = this.productClientRest.findById(productId);
			return new Item(product, 1);

		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error id no debe ser null. Error: " + e);
		}
	}

	@Override
	public Item findByProductIdAndAmount(Long productId, Integer amount) {
		try {
			Product product = this.productClientRest.findById(productId);
			return new Item(product, amount);

		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error id no debe ser null. Error: " + e);
		}
	}

	@Override
	public Product saveProduct(Product product) {
		return this.productClientRest.create(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return this.productClientRest.update(product);
	}

	@Override
	public Boolean deleteProduct(Long productId) {

		return this.productClientRest.delete(productId);
	}

}

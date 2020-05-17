package com.springboot.app.items.service;

import java.util.List;

import com.springboot.app.commons.models.Product;
import com.springboot.app.items.models.Item;

public interface ItemService {

	List<Item> findAll();

	Item findByProductId(Long productId);

	Item findByProductIdAndAmount(Long productId, Integer amount);

	Product saveProduct(Product product);

	Product updateProduct(Product product);

	Boolean deleteProduct(Long productId);
}

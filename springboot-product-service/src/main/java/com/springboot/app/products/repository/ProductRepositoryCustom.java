package com.springboot.app.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.springboot.app.products.models.Product;


@Repository
public interface ProductRepositoryCustom {

	Page<Product> findByNameQuery(String name, Pageable pageable);
}

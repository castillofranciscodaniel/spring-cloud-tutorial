package com.springboot.app.products.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.app.commons.models.Product;
import com.springboot.app.commons.service.GenericService;

public interface ProductService extends GenericService<Product> {

	Page<Product> findByName(String name, Pageable pageable);

	Page<Product> findByNameSpecification(String name, Pageable pageable);

	Page<Product> ByNameQueryJpa(String name, Pageable pageable);

	List<Product> findByNameQueryHDL(String name);

}
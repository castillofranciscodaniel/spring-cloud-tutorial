package com.springboot.app.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.commons.service.GenericServiceImpl;
import com.springboot.app.products.models.Product;
import com.springboot.app.products.repository.ProductRepository;
import com.springboot.app.products.repository.ProductSpecification;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		super(productRepository);
		this.productRepository = productRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findByName(String name, Pageable pageable) {
		return this.productRepository.findByName(name, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findByNameSpecification(String name, Pageable pageable) {
		return this.productRepository.findAll(ProductSpecification.hashName(name), pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> ByNameQueryJpa(String name, Pageable pageable) {
		return this.productRepository.findByNameQuery(name, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findByNameQueryHDL(String name) {
		return this.productRepository.findByNameQueryHDL(name);
	}

}

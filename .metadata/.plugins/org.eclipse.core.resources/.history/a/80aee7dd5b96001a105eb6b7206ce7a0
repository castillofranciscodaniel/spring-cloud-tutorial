package com.springboot.app.products.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.app.commons.models.Model;
import com.springboot.app.commons.service.ResourceNotFoundException;

public interface GenericService<T extends Model> {

	T save(T t) throws ResourceNotFoundException;

	void saveAll(List<T> t);

	Boolean delete(T t);

	Boolean delete(Long id);

	Boolean deleteAll(List<T> listT);

	T findById(Long id);

	List<T> findAll();

	Page<T> findAll(Pageable pageable);
}

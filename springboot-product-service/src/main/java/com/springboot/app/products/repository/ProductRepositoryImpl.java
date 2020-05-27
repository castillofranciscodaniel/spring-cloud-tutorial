package com.springboot.app.products.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.springboot.app.products.models.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Page<Product> findByNameQuery(String name, Pageable pageable) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		Root<Product> rootProduct = cq.from(Product.class);

		Expression<String> filterKeyExp = rootProduct.get("name").as(String.class);
		filterKeyExp = cb.lower(filterKeyExp);

		cq.where(cb.like(filterKeyExp, name));
		TypedQuery<Product> query = entityManager.createQuery(cq);
		
		List<Product> list = query.getResultList();
		Page<Product> page = new PageImpl<Product>(list, pageable, list.size());
		return page;
	}

}

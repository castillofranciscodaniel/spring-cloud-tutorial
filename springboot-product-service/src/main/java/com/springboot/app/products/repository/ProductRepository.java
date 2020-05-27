package com.springboot.app.products.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.products.models.Product;

/**
 * 
 * @author fraaan JpaSpecificationExecutor<Product> solo debemos extenderla si
 *         vamos a usar especificaciones en las querys... Si no.. NO!
 */
@Repository
public interface ProductRepository
		extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>, ProductRepositoryCustom {

	Page<Product> findByName(String name, Pageable pageable);

	/**
	 * DEBERÍAMOS NO USARLA NUNCA.. AL MENOS QUE HAGAMOS ALGO QUE NO NECESITE SER
	 * PAGINADO. Ejemplo: resumenes e informes
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "SELECT new com.springboot.app.products.models.Product(p.id, p.name, p.price) "
			+ "FROM Product p WHERE name LIKE :name")
	List<Product> findByNameQueryHDL(String name);

}

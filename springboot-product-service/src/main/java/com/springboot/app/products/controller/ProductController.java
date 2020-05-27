package com.springboot.app.products.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.commons.controller.GenericController;
import com.springboot.app.commons.models.util.ErrorBody;
import com.springboot.app.products.models.Product;
import com.springboot.app.products.service.ProductService;

@RestController
@RequestMapping(value = "", name = "productController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends GenericController<Product, Long> {

	private ProductService productService;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	ProductController(ProductService productService) {
		super(productService);
		this.productService = productService;
	}

	@Override
	@GetMapping(name = "listAll", path = "listAll")
	public ResponseEntity<List<Product>> list() {
		return ResponseEntity.ok(this.productService.findAll().stream().map(product -> {
			product.setPort(this.port);
			return product;
		}).collect(Collectors.toList()));
	}

	@Override
	@GetMapping(name = "findById", path = "{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Product product = this.productService.findById(id);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ErrorBody("Id: %s no encontrado", id));
		}
		product.setPort(this.port);

		/*
		 * Código para mostrar como la configuracion de ribbon y hystrix en el
		 * application.properties funciona con los timeOut
		 */
//
//		try {
//			Thread.sleep(2000l);
//		} catch (InterruptedException e) {
			// En ribbon y hystrix el tiempo por defecto de timeOut, es de 1 segundo.
			// Esto ejecutaríA el camino por defecto que configure en hystrix en la
			// petición del backend de item
//			e.printStackTrace();
//		}

		return ResponseEntity.status(200).body(product);
	}

	@GetMapping(params = "find=ByName")
	public ResponseEntity<Page<Product>> list(@RequestParam("name") String name, Pageable pageable) {
		return ResponseEntity.ok(this.productService.findByName(name, pageable));
	}

	@GetMapping(params = "find=ByNameSpecification")
	public ResponseEntity<Page<Product>> listSpecification(@RequestParam("name") String name, Pageable pageable) {
		return ResponseEntity.ok(this.productService.findByNameSpecification(name, pageable));
	}

	@GetMapping(params = "find=ByNameQueryJpa")
	public ResponseEntity<Page<Product>> listQuery(@RequestParam("name") String name, Pageable pageable) {
		return ResponseEntity.ok(this.productService.ByNameQueryJpa(name, pageable));
	}

	@GetMapping(params = "find=ByNameQueryHdl")
	public ResponseEntity<List<Product>> listQueryHdl(@RequestParam("name") String name) {
		return ResponseEntity.ok(this.productService.findByNameQueryHDL(name));
	}
}

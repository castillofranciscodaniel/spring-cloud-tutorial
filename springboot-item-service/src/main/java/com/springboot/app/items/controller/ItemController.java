package com.springboot.app.items.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.Product;
import com.springboot.app.items.models.util.ErrorBody;
import com.springboot.app.items.service.ItemService;
import com.springboot.app.items.service.ResourceNotFoundException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// @RequestMapping(value = "", name = "itemController", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@RefreshScope
@RestController
@RequiredArgsConstructor
public class ItemController {

	@NonNull
	private final ItemService itemService;

	@Value("${config.text}")
	private String text;

	private final Environment enviroment;

	@GetMapping(name = "list")
	public ResponseEntity<List<Item>> list() {
		return ResponseEntity.ok(this.itemService.findAll());
	}

	@GetMapping(params = "find=ByProductId")
	public ResponseEntity<Item> findByProductId(@RequestParam("productId") Long productId) {
		return ResponseEntity.ok(this.itemService.findByProductId(productId));
	}

	@HystrixCommand(fallbackMethod = "alternativeMethod")
	@GetMapping(params = "find=ByProductIdAndAmount")
	public ResponseEntity<Item> findByProductIdAndAmount(
			@RequestParam("productId") Long productId,
			@RequestParam("amount") Integer amount) {
		return ResponseEntity.ok(this.itemService.findByProductIdAndAmount(productId, amount));
	}

	public ResponseEntity<Item> alternativeMethod(@RequestParam("productId") Long productId,
			@RequestParam("amount") Integer amount) {
		Product product = new Product();
		product.setId(productId);
		product.setName("Camara Sony");
		product.setPrice(500.00);
		Item item = new Item(product, amount);
		return ResponseEntity.ok(item);
	}

	@GetMapping(path = "getConfig")
	public ResponseEntity<Map<String, String>> getConfig(@Value("${server.port}") String port) {

		log.info("text: {}, port: {}", this.text, port);
		Map<String, String> map = new HashMap<>();
		map.put("text", this.text);
		map.put("puerto", port);
		
		String[] activeProfiles = this.enviroment.getActiveProfiles();
		if (activeProfiles.length > 0 && activeProfiles[0].equals("dev")) {
			map.put("author.nombre", enviroment.getProperty("config.author.name"));
			map.put("author.mail", enviroment.getProperty("config.author.mail"));
		}
		return ResponseEntity.ok(map);
	}

	@PostMapping(name = "createProduct", path = "product")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result)
			throws ResourceNotFoundException {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		if (product.getId() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorBody("%s no puede tener Id", product));
		}

		Product newProduct = this.itemService.saveProduct(product);

		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}

	@PutMapping(name = "updateProduct", path = "product")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, BindingResult result)
			throws ResourceNotFoundException {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}
		if (product.getId() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorBody("%s necesita un Id", product));
		}

		Product newProduct = this.itemService.updateProduct(product);

		if (newProduct == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al actualizar");
		}
		return ResponseEntity.ok().body(newProduct);

	}

	@DeleteMapping(name = "deleteProductById", path = "product/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
		this.itemService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

}

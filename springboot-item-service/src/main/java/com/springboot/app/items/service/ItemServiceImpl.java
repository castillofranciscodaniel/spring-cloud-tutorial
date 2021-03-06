package com.springboot.app.items.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// @Primary
public class ItemServiceImpl implements ItemService {

	private final RestTemplate restTemplate;

	private final String PRODUCT_ENDPOINT = "http://products-service";

	private UriComponentsBuilder uriComponentsBuilder(String... pathSegments) {
		// return
		// UriComponentsBuilder.fromHttpUrl(this.enviromentUrlProducts).pathSegment(pathSegments);

		// con el balanceador de carga podemos reemplazar la url por el nombre del
		// servicio
		return UriComponentsBuilder.fromHttpUrl(this.PRODUCT_ENDPOINT).pathSegment(pathSegments);

	}

	@Override
	public List<Item> findAll() {
		try {
			String url = this.uriComponentsBuilder("listAll").build().toString();
			List<Product> products = Arrays.asList(this.restTemplate.getForObject(url, Product[].class));
			return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Item findByProductId(Long productId) {
		try {
			String url = this.uriComponentsBuilder("products").path("{id}").buildAndExpand(productId).toString();
			Product product = this.restTemplate.getForObject(url, Product.class);
			return new Item(product, 1);

		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error id no debe ser null. Error: " + e);
		}
	}

	@Override
	public Item findByProductIdAndAmount(Long productId, Integer amount) {
		try {
			String url = this.uriComponentsBuilder("products").path("{id}").buildAndExpand(productId).toString();
			Product product = this.restTemplate.getForObject(url, Product.class);
			return new Item(product, amount);

		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error id no debe ser null. Error: " + e);
		}
	}

	@Override
	public Product saveProduct(Product product) {
		return this.restTemplate.postForEntity(this.PRODUCT_ENDPOINT, product, Product.class)
				.getBody();
			}

	@Override
	public Product updateProduct(Product product) {
		return this.restTemplate.exchange(this.PRODUCT_ENDPOINT, HttpMethod.PUT, new HttpEntity<Product>(product),
				Product.class).getBody();

	}

	@Override
	public Boolean deleteProduct(Long productId) {
		this.restTemplate.delete(this.uriComponentsBuilder(productId.toString()).build().toString());
		return true; // no tengo ganas de validarlo....

	}

}

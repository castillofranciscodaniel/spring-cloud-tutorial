package com.springboot.app.items.clients;

import org.springframework.cloud.openfeign.FeignClient;

import com.springboot.app.commons.clients.GenericClientRest;
import com.springboot.app.commons.models.Product;

// la primera opción es indicandole la url con la que se tiene que conectar, pero con 
// @RibbonClient no hace falta.. porque la saca de la lista de url posibles del 
// application.properties
// @FeignClient(name = "products-service", url = "${service.product.url}" + "/products")

@FeignClient(name = "products-service")

public interface ProductClientRest extends GenericClientRest<Product> {
}

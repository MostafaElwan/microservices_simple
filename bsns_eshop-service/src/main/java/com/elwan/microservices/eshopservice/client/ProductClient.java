package com.elwan.microservices.eshopservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.elwan.microservices.eshopservice.client.fallback.ProductClientFallback;
import com.elwan.microservices.eshopservice.dto.Product;

@FeignClient(name="product-service", fallback = ProductClientFallback.class)
public interface ProductClient {

	@GetMapping(value = "/products/all/{from}/to/{to}")
	public List<Product> findProducts(@PathVariable int from, @PathVariable int to);
	
	@GetMapping(value = "/products/count")
	public long getProductsCount();
	
}

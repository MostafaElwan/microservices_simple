package com.elwan.microservices.productservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elwan.microservices.productservice.domain.Product;
import com.elwan.microservices.productservice.service.ProductService;


@RestController
public class ProductAPI {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = {"/products/id/{id}"})
	public Product findProductById(@PathVariable long id) {
		Product p = productService.findById(id);
		return p;
	}
	
	@GetMapping(value = "/products/all/{from}/to/{to}")
	public List<Product> findAll(@PathVariable int from, @PathVariable int to) {
		List<Product> products = productService.findAll(from, to);
		return products;
	}
	
	@GetMapping(value = "/products/count")
	public long getCount() {
		long count = productService.count();
		return count;
	}
	
	
	
}

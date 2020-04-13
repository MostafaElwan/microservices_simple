package com.elwan.microservices.productservice.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elwan.microservices.productservice.domain.Product;
import com.elwan.microservices.productservice.service.ProductService;


@RestController
public class ProductAPI {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = {"/products/id/{id}"})
	public Product findProductById(@PathVariable long id) {
		logger.info("findProductById just started ...");
		Product p = productService.findById(id);
		logger.info("findProductById finished successfully");
		return p;
	}
	
	@GetMapping(value = "/products/all/{from}/to/{to}")
	public List<Product> findAll(@PathVariable int from, @PathVariable int to) {
		logger.info("findAll just started ...");
		List<Product> products = productService.findAll(from, to);
		logger.info("findAll finished successfully");
		return products;
	}
	
	@GetMapping(value = "/products/count")
	public long getCount() {
		logger.info("getCount just started ...");
		long count = productService.count();
		logger.info("getCount finished successfully");
		return count;
	}
	
	
	
}

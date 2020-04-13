package com.elwan.microservices.orderservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elwan.microservices.orderservice.domain.ShoppingCart;
import com.elwan.microservices.orderservice.service.ShoppingCartService;


@RestController
public class OrderAPI {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping(value = {"/cart/customer/{customerId}/product/{productId}"})
	public void addProductToCart(@PathVariable String customerId, @PathVariable Long productId) throws Exception {
		logger.info("addProductToCart just started ...");
		ShoppingCart cart = shoppingCartService.findByCustomerId(customerId);
		if(cart == null)
			cart = shoppingCartService.create(customerId);
		
		shoppingCartService.addProduct(cart, productId);
		logger.info("addProductToCart finished successfully");
	}
	
	@PostMapping(value = {"/cart/{cartId}/cartitem/{itemId}/delete"})
	public ShoppingCart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
		logger.info("removeItemFromCart just started ...");
		shoppingCartService.deleteItem(itemId);
		ShoppingCart cart = shoppingCartService.findById(cartId);
		logger.info("removeItemFromCart finished successfully");
		return cart;
	}
	
	@GetMapping(value = {"/cart/customer/{customerId}/"})
	public ShoppingCart getCustomerCart(@PathVariable String customerId) {
		logger.info("getCustomerCart just started ...");
		ShoppingCart cart = shoppingCartService.findByCustomerId(customerId);
		cart.getItems();
		logger.info("getCustomerCart finished successfully");
		return cart;
	}
}

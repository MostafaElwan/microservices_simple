package com.elwan.microservices.orderservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elwan.microservices.orderservice.config.Configuration;
import com.elwan.microservices.orderservice.domain.ShoppingCart;
import com.elwan.microservices.orderservice.service.OrderService;
import com.elwan.microservices.orderservice.service.ShoppingCartService;


@RestController
public class OrderAPI {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private Configuration config;
	
	@GetMapping(value = {"/cart/customer/{customerId}/product/{productId}"})
	public void addProductToCart(@PathVariable String customerId, @PathVariable Long productId) throws Exception {
		ShoppingCart cart = shoppingCartService.findByCustomerId(customerId);
		if(cart == null)
			cart = shoppingCartService.create(customerId);
		
		shoppingCartService.addProduct(cart, productId);
	}
	
	@PostMapping(value = {"/cart/{cartId}/cartitem/{itemId}/delete"})
	public ShoppingCart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
		shoppingCartService.deleteItem(itemId);
		ShoppingCart cart = shoppingCartService.findById(cartId);
		return cart;
	}
	
	@GetMapping(value = {"/cart/customer/{customerId}/"})
	public ShoppingCart getCustomerCart(@PathVariable String customerId) {
		ShoppingCart cart = shoppingCartService.findByCustomerId(customerId);
		cart.getItems();
		return cart;
	}
}

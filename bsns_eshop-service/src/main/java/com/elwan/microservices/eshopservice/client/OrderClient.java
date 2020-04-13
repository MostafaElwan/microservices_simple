package com.elwan.microservices.eshopservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.elwan.microservices.eshopservice.client.fallback.OrderClientFallback;
import com.elwan.microservices.eshopservice.dto.ShoppingCart;

@FeignClient(name="order-service", fallback = OrderClientFallback.class)
public interface OrderClient {

	@GetMapping(value = {"/cart/customer/{customerId}/product/{productId}"})
	public void addProductToCart(@PathVariable String customerId, @PathVariable Long productId);
	
	@PostMapping(value = {"/cart/{cartId}/cartitem/{itemId}/delete"})
	public ShoppingCart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId);
	
	@GetMapping(value = {"/cart/customer/{customerId}/"})
	public ShoppingCart getCustomerCart(@PathVariable String customerId);
	
}

package com.elwan.microservices.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="order-service")
public interface OrderClient {

	@GetMapping(value = {"/cart/customer/{customerId}/product/{productId}"})
	public void addProductToCart(@PathVariable String customerId, @PathVariable Long productId);
	
}

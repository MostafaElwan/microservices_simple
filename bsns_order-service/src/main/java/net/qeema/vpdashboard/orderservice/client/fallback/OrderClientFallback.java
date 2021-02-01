package com.elwan.microservices.orderservice.client.fallback;

import com.elwan.microservices.orderservice.client.OrderClient;

public class OrderClientFallback implements OrderClient {

	@Override
	public void addProductToCart(String customerId, Long productId) {
	}

}

package com.elwan.microservices.eshopservice.client.fallback;

import com.elwan.microservices.eshopservice.client.OrderClient;
import com.elwan.microservices.eshopservice.dto.ShoppingCart;

public class OrderClientFallback implements OrderClient {

	@Override
	public void addProductToCart(String customerId, Long productId) {
	}

	@Override
	public ShoppingCart removeItemFromCart(Long cartId, Long itemId) {
		return ShoppingCart.NA;
	}

	@Override
	public ShoppingCart getCustomerCart(String customerId) {
		return ShoppingCart.NA;
	}

}

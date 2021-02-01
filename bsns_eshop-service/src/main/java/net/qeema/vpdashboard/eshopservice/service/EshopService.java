package com.elwan.microservices.eshopservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elwan.microservices.eshopservice.client.CustomerClient;
import com.elwan.microservices.eshopservice.client.OrderClient;
import com.elwan.microservices.eshopservice.client.ProductClient;
import com.elwan.microservices.eshopservice.dto.Customer;
import com.elwan.microservices.eshopservice.dto.Product;
import com.elwan.microservices.eshopservice.dto.ShoppingCart;
 
@Service
public class EshopService {
    
	@Autowired
    private CustomerClient customerClient;
	
	@Autowired
    private ProductClient productClient;
	
	@Autowired
    private OrderClient orderClient;
	
	public Customer findCustomerByEmail(String email) {
		Customer c = customerClient.findCustomerByEmail(email);
		return c;
	}
	
	public List<Product> findProducts(int from, int to) {
		List<Product> products = productClient.findProducts(from, to);
		return products;
	}
	
	public long getProductsCount() {
		long count = productClient.getProductsCount();
		return count;
	}

	public void addProductToCart(String customerId, long productId) {
		orderClient.addProductToCart(customerId, productId);
	}
	
	public ShoppingCart getCustomerCart(String customerId) {
		ShoppingCart cart = orderClient.getCustomerCart(customerId);
		return cart;
	}

	public ShoppingCart deleteCartItem(Long cartId, Long cartItemId) {
		ShoppingCart cart = orderClient.removeItemFromCart(cartId, cartItemId);
		return cart;
	}
}
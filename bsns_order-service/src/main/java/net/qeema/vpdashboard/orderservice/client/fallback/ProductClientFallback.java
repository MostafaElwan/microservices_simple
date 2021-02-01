package com.elwan.microservices.orderservice.client.fallback;

import java.util.ArrayList;
import java.util.List;

import com.elwan.microservices.orderservice.client.ProductClient;
import com.elwan.microservices.orderservice.dto.Product;

public class ProductClientFallback implements ProductClient {

	@Override
	public Product findProductById(long id) {
		return Product.NA;
	}

	@Override
	public List<Product> findProducts(int from, int to) {
		return new ArrayList<Product>();
	}

	@Override
	public long getProductsCount() {
		return 0;
	}

}

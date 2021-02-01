package com.elwan.microservices.eshopservice.client.fallback;

import java.util.ArrayList;
import java.util.List;

import com.elwan.microservices.eshopservice.client.ProductClient;
import com.elwan.microservices.eshopservice.dto.Product;

public class ProductClientFallback implements ProductClient {

	@Override
	public List<Product> findProducts(int from, int to) {
		return new ArrayList<Product>();
	}

	@Override
	public long getProductsCount() {
		return 0;
	}

}

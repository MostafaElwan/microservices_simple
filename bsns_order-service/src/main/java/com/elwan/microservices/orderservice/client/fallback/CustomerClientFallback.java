package com.elwan.microservices.orderservice.client.fallback;

import com.elwan.microservices.orderservice.client.CustomerClient;
import com.elwan.microservices.orderservice.dto.Customer;

public class CustomerClientFallback implements CustomerClient {

	@Override
	public Customer findCustomerById(String id) {
		return Customer.NA;
	}

}

package com.elwan.microservices.eshopservice.client.fallback;

import com.elwan.microservices.eshopservice.client.CustomerClient;
import com.elwan.microservices.eshopservice.dto.Customer;

public class CustomerClientFallback implements CustomerClient {

	@Override
	public Customer findCustomerByEmail(String email) {
		return Customer.NA;
	}

}

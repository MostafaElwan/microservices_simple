package com.elwan.microservices.eshopservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.elwan.microservices.eshopservice.client.fallback.CustomerClientFallback;
import com.elwan.microservices.eshopservice.dto.Customer;

@FeignClient(name="customer-service", fallback = CustomerClientFallback.class)
public interface CustomerClient {

	@GetMapping(value = {"/customers/email/{email}"})
	public Customer findCustomerByEmail(@PathVariable String email);
	
}

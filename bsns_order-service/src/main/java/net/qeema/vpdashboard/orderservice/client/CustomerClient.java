package com.elwan.microservices.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.elwan.microservices.orderservice.dto.Customer;

@FeignClient(name="customer-service")
public interface CustomerClient {

	@GetMapping(value = {"/customers/id/{id}"})
	public Customer findCustomerById(@PathVariable String id);
	
}

package com.elwan.microservices.customerservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elwan.microservices.customerservice.config.Configuration;
import com.elwan.microservices.customerservice.domain.Customer;
import com.elwan.microservices.customerservice.service.CustomerService;


@RestController
public class CustomerAPI {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private Configuration config;
	
	@GetMapping(value = {"/customers/id/{id}"})
	public Customer findCustomerById(@PathVariable String id) {
		Customer c = customerService.findById(id);
		return c;
	}
	
	@GetMapping(value = {"/customers/email/{email}"})
	public Customer getCustomerByEmail(@PathVariable(name = "email") String email) {
		Customer c = customerService.findByEmail(email);
		return c;
	}
	
}

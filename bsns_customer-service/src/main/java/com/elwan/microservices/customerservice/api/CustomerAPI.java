package com.elwan.microservices.customerservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elwan.microservices.customerservice.domain.Customer;
import com.elwan.microservices.customerservice.service.CustomerService;


@RestController
public class CustomerAPI {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = {"/customers/id/{id}"})
	public Customer findCustomerById(@PathVariable String id) {
		logger.info("findCustomerById just called ...");
		Customer c = customerService.findById(id);
		logger.info("findCustomerById finished successfully");
		return c;
	}
	
	@GetMapping(value = {"/customers/email/{email}"})
	public Customer getCustomerByEmail(@PathVariable(name = "email") String email) {
		logger.info("getCustomerByEmail just called ...");
		Customer c = customerService.findByEmail(email);
		logger.info("getCustomerByEmail finished successfully");
		return c;
	}
	
}

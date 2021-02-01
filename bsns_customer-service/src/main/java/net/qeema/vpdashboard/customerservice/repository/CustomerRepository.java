package com.elwan.microservices.customerservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.elwan.microservices.customerservice.domain.Customer;
 
@Repository
public interface CustomerRepository
	extends PagingAndSortingRepository<Customer, String>, MongoRepository<Customer, String> {
	
	Optional<Customer> findByEmail(String email);
}

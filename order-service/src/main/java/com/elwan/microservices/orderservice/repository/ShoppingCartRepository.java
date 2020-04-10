package com.elwan.microservices.orderservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.elwan.microservices.orderservice.domain.ShoppingCart;
 
public interface ShoppingCartRepository 
	extends PagingAndSortingRepository<ShoppingCart, Long>, JpaSpecificationExecutor<ShoppingCart> {

	Optional<ShoppingCart> findByCustomerId(String customerId);
}

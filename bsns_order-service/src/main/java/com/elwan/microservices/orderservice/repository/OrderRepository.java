package com.elwan.microservices.orderservice.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.elwan.microservices.orderservice.domain.Order;
 
public interface OrderRepository 
	extends PagingAndSortingRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}

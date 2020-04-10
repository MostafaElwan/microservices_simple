package com.elwan.microservices.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.elwan.microservices.orderservice.domain.ShoppingCartItem;
 
public interface ShoppingCartItemRepository 
	extends PagingAndSortingRepository<ShoppingCartItem, Long>, JpaSpecificationExecutor<ShoppingCartItem> {

	List<ShoppingCartItem> findByCartId(Long cartId);
}

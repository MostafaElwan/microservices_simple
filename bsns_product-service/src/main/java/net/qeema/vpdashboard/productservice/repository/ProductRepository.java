package com.elwan.microservices.productservice.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.elwan.microservices.productservice.domain.Product;
 
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, 
        JpaSpecificationExecutor<Product> {
}

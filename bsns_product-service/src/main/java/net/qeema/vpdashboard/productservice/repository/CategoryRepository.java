package com.elwan.microservices.productservice.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.elwan.microservices.productservice.domain.Category;
 
public interface CategoryRepository 
	extends PagingAndSortingRepository<Category, Long>, JpaSpecificationExecutor<Category> {
}

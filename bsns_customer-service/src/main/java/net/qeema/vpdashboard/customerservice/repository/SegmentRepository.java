package com.elwan.microservices.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.elwan.microservices.customerservice.domain.Segment;
 
@Repository
public interface SegmentRepository
	extends PagingAndSortingRepository<Segment, String>, MongoRepository<Segment, String> {
}

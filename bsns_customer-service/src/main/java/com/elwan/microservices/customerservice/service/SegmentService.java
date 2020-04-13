package com.elwan.microservices.customerservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.elwan.microservices.customerservice.domain.Segment;
import com.elwan.microservices.customerservice.repository.SegmentRepository;
 
@Service
public class SegmentService {
    
    @Autowired
    private SegmentRepository segmentRepository;
    
    private boolean existsById(String id) {
        return segmentRepository.existsById(id);
    }
    
    public Segment findById(String id) {
        return segmentRepository.findById(id).orElse(null);
    }
    
    public List<Segment> findAll(int pageNumber, int rowPerPage) {
        List<Segment> segments = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("createdAt").descending());
        segmentRepository.findAll(sortedByLastUpdateDesc).forEach(segments::add);
        return segments;
    }
    
    public Segment save(Segment segment) throws Exception {
        if (StringUtils.isEmpty(segment.getName()))
            throw new Exception("Name is required");
        
        return segmentRepository.save(segment);
    }
    
    public void update(Segment segment) throws Exception {
    	if (StringUtils.isEmpty(segment.getName()))
            throw new Exception("Name is required");
        
        if (!existsById(segment.getId()))
            throw new Exception("Cannot find a segment with id: " + segment.getId());
        
        Segment origin = findById(segment.getId());
        segment.fill(origin);
        
        segmentRepository.save(segment);
    }
    
    public void deleteById(String id) throws Exception {
    	Segment segment = findById(id);
        if (segment == null) 
            throw new Exception("Cannot find a segment with id: " + id);
        
        if(!segment.getCustomers().isEmpty())
        	throw new Exception("Segment cannot be deleted, as there are customers assigned to!");
        
        segmentRepository.deleteById(id);
    }
    
    public Long count() {
        return segmentRepository.count();
    }
}
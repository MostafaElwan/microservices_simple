package com.elwan.microservices.customerservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.elwan.microservices.customerservice.domain.Customer;
import com.elwan.microservices.customerservice.repository.CustomerRepository;
 
@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    private boolean existsById(String id) {
        return customerRepository.existsById(id);
    }
    
    public Customer findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }
    
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }
    
    public List<Customer> findAll(int pageNumber, int rowPerPage) {
        List<Customer> customers = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("createdAt").descending());
        customerRepository.findAll(sortedByLastUpdateDesc).forEach(customers::add);
        return customers;
    }
    
    public Customer save(Customer customer) throws Exception {
    	
    	if (StringUtils.isEmpty(customer.getSegment()) || StringUtils.isEmpty(customer.getSegment().getId()) )
            throw new Exception("Segment is required");
    	
        if (StringUtils.isEmpty(customer.getFirstName()))
            throw new Exception("First name is required");
        
        if (StringUtils.isEmpty(customer.getLastName()))
            throw new Exception("Last name is required");
        
        if (StringUtils.isEmpty(customer.getLastName()))
            throw new Exception("Last name is required");
        
        return customerRepository.save(customer);
    }
    
    public void update(Customer customer) throws Exception {
    	
    	if (StringUtils.isEmpty(customer.getSegment()) || StringUtils.isEmpty(customer.getSegment().getId()) )
            throw new Exception("Segment is required");

    	if (StringUtils.isEmpty(customer.getFirstName()))
            throw new Exception("First name is required");
    	
        if (StringUtils.isEmpty(customer.getLastName()))
            throw new Exception("Last name is required");
        
        if (customer.getAge() == null)
            throw new Exception("Age is required");
        
        try {
        	customer.getAge().intValue();
        } catch (NumberFormatException e) {
        	throw new Exception("Age must be a number");
		}
        
        if (!existsById(customer.getId()))
            throw new Exception("Cannot find a customer with id: " + customer.getId());
        
        Customer origin = findById(customer.getId());
        customer.fill(origin);
        
        customerRepository.save(customer);
    }
    
    public void deleteById(String id) throws Exception {
        if (!existsById(id)) 
            throw new Exception("Cannot find a customer with id: " + id);
        
        customerRepository.deleteById(id);
    }
    
    public Long count() {
        return customerRepository.count();
    }
    
}
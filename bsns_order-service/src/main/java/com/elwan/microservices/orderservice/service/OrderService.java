package com.elwan.microservices.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.elwan.microservices.orderservice.domain.Order;
import com.elwan.microservices.orderservice.repository.OrderRepository;
 
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    private boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }
    
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    
    public List<Order> findAll(int pageNumber, int rowPerPage) {
        List<Order> orders = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("createdAt").descending());
        orderRepository.findAll(sortedByLastUpdateDesc).forEach(orders::add);
        return orders;
    }
    
    public Order save(Order order) throws Exception {
        return orderRepository.save(order);
    }
    
    public void update(Order order) throws Exception {
        if (!existsById(order.getId())) 
            throw new Exception("Cannot find a order with id: " + order.getId());

        orderRepository.save(order);
    }
    
    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) 
            throw new Exception("Cannot find a order with id: " + id);
            
        orderRepository.deleteById(id);
    }
    
    public Long count() {
        return orderRepository.count();
    }
}
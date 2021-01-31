package com.elwan.microservices.productservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.elwan.microservices.productservice.domain.Product;
import com.elwan.microservices.productservice.repository.ProductRepository;
 
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
    
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    public List<Product> findAll(int pageNumber, int rowPerPage) {
        List<Product> products = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("createdAt").descending());
        productRepository.findAll(sortedByLastUpdateDesc).forEach(products::add);
        return products;
    }
    
    public Product save(Product product) throws Exception {
        if (StringUtils.isEmpty(product.getName()))
            throw new Exception("Name is required");
        
        if (StringUtils.isEmpty(product.getPrice()))
            throw new Exception("Price is required");
        
        return productRepository.save(product);
    }
    
    public void update(Product product) throws Exception {
        if (StringUtils.isEmpty(product.getName()))
            throw new Exception("Name is required");

        if (StringUtils.isEmpty(product.getPrice()))
            throw new Exception("Price is required");

        if (!existsById(product.getId())) 
            throw new Exception("Cannot find a product with id: " + product.getId());

        productRepository.save(product);
    }
    
    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) 
            throw new Exception("Cannot find a product with id: " + id);
            
        productRepository.deleteById(id);
    }
    
    public Long count() {
        return productRepository.count();
    }
}
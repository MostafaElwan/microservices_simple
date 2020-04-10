package com.elwan.microservices.productservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.elwan.microservices.productservice.domain.Category;
import com.elwan.microservices.productservice.repository.CategoryRepository;
 
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    private boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
    
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
    public List<Category> findAll(int pageNumber, int rowPerPage) {
        List<Category> categorys = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("createdAt").descending());
        categoryRepository.findAll(sortedByLastUpdateDesc).forEach(categorys::add);
        return categorys;
    }
    
    public Category save(Category category) throws Exception {
        if (StringUtils.isEmpty(category.getName()))
            throw new Exception("Name is required");
        
        return categoryRepository.save(category);
    }
    
    public void update(Category category) throws Exception {
        if (StringUtils.isEmpty(category.getName()))
            throw new Exception("Name is required");

        if (!existsById(category.getId()))
            throw new Exception("Cannot find a category with id: " + category.getId());
        
        categoryRepository.save(category);
    }
    
    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) 
            throw new Exception("Cannot find a category with id: " + id);
        
        categoryRepository.deleteById(id);
    }
    
    public Long count() {
        return categoryRepository.count();
    }
}
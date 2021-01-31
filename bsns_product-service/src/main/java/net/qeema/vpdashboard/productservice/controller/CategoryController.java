package com.elwan.microservices.productservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elwan.microservices.productservice.config.Configuration;
import com.elwan.microservices.productservice.domain.Category;
import com.elwan.microservices.productservice.service.CategoryService;

 
@Controller
public class CategoryController {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private CategoryService categoryService;
 
    @Autowired
    private Configuration config;
     
    @GetMapping(value = "/categories")
    public String getCategories(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
    	
        List<Category> categories = categoryService.findAll(pageNumber, config.getRowsPerPage());
     
        long count = categoryService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
        model.addAttribute("categories", categories);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "category-list";
    }
 
    @GetMapping(value = "/categories/{categoryId}")
    public String getCategoryById(Model model, @PathVariable long categoryId) {
        Category category = null;
        try {
            category = categoryService.findById(categoryId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("category", category);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "category";
    }
 
    @GetMapping(value = {"/categories/add"})
    public String showAddCategory(Model model) {
        Category category = new Category();
        model.addAttribute("add", true);
        model.addAttribute("category", category);
     
        model.addAttribute("contextPath", config.getContextPath());
        return "category-edit";
    }
     
    @PostMapping(value = "/categories/add")
    public String addCategory(Model model, @ModelAttribute("category") Category category) {        
        try {
            Category newCategory = categoryService.save(category);
            return "redirect:"+config.getContextPath()+"/categories/" + String.valueOf(newCategory.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", true);
            return "category-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
     
    @GetMapping(value = {"/categories/{categoryId}/edit"})
    public String showEditCategory(Model model, @PathVariable long categoryId) {
        Category category = null;
        try {
            category = categoryService.findById(categoryId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("category", category);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "category-edit";
    }
     
    @PostMapping(value = {"/categories/{categoryId}/edit"})
    public String updateCategory(Model model, @PathVariable long categoryId, @ModelAttribute("category") Category category) {
        try {
            category.setId(categoryId);
            categoryService.update(category);
            return "redirect:"+config.getContextPath()+"/categories/" + String.valueOf(category.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", false);
            return "category-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
 
    @GetMapping(value = {"/categories/{categoryId}/delete"})
    public String showDeleteCategoryById(Model model, @PathVariable long categoryId) {
        Category category = null;
        try {
            category = categoryService.findById(categoryId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("category", category);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "category";
    }
     
    @PostMapping(value = {"/categories/{categoryId}/delete"})
    public String deleteCategoryById(Model model, @PathVariable long categoryId) {
        try {
            categoryService.deleteById(categoryId);
            return "redirect:"+config.getContextPath()+"/categories";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "category";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
    
}
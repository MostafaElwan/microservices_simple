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
import com.elwan.microservices.productservice.domain.Product;
import com.elwan.microservices.productservice.service.CategoryService;
import com.elwan.microservices.productservice.service.ProductService;
 
@Controller
public class ProductController {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
 
    @Autowired
    private Configuration config;
     
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", config.getAppTitle());
        model.addAttribute("contextPath", config.getContextPath());
        return "index";
    }
 
    @GetMapping(value = "/products")
    public String getProducts(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
    	
        List<Product> products = productService.findAll(pageNumber, config.getRowsPerPage());
     
        long count = productService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
        model.addAttribute("products", products);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "product-list";
    }
    
    @GetMapping(value = {"/products/category/{categoryId}"})
    public String showCategoryProducts(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber, @PathVariable Long categoryId) {
    	
    	Category category = categoryService.findById(categoryId);
    	List<Product> products = category.getProducts();
    	
    	long count = products.size();
    	boolean hasPrev = pageNumber > 1;
    	boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
    	model.addAttribute("products", products);
    	model.addAttribute("hasPrev", hasPrev);
    	model.addAttribute("prev", pageNumber - 1);
    	model.addAttribute("hasNext", hasNext);
    	model.addAttribute("next", pageNumber + 1);
    	
    	model.addAttribute("contextPath", config.getContextPath());
    	return "product-list";
    }
 
    @GetMapping(value = "/products/{productId}")
    public String getProductById(Model model, @PathVariable long productId) {
        Product product = null;
        try {
            product = productService.findById(productId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("product", product);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "product";
    }
 
    @GetMapping(value = {"/products/add"})
    public String showAddProduct(Model model) {
    	
    	List<Category> categories = categoryService.findAll(1, 100);
        model.addAttribute("categories", categories);
        
        Product product = new Product();
        model.addAttribute("add", true);
        model.addAttribute("product", product);
     
        model.addAttribute("contextPath", config.getContextPath());
        return "product-edit";
    }
     
    @PostMapping(value = "/products/add")
    public String addProduct(Model model, @RequestParam("selectedCategory") Long selectedCategoryId, @ModelAttribute("product") Product product) {        
        try {
        	Category selectedCategory = categoryService.findById(selectedCategoryId);
        	
        	product.setCategory(selectedCategory);
        	
            Product newProduct = productService.save(product);
            categoryService.update(selectedCategory);
            
            return "redirect:"+config.getContextPath()+"/products/" + String.valueOf(newProduct.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            
            List<Category> categories = categoryService.findAll(1, 100);
            model.addAttribute("categories", categories);
     
            model.addAttribute("add", true);
            return "product-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
     
    @GetMapping(value = {"/products/{productId}/edit"})
    public String showEditProduct(Model model, @PathVariable long productId) {
        Product product = null;
        try {
        	List<Category> categories = categoryService.findAll(1, 100);
            model.addAttribute("categories", categories);
            
            product = productService.findById(productId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("product", product);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "product-edit";
    }
     
    @PostMapping(value = {"/products/{productId}/edit"})
    public String updateProduct(Model model, @RequestParam("selectedCategory") Long selectedCategoryId, @PathVariable long productId, @ModelAttribute("product") Product product) {
        try {
        	Category selectedCategory = categoryService.findById(selectedCategoryId);
        	
            product.setId(productId);
            product.setCategory(selectedCategory);
            
            productService.update(product);
            return "redirect:"+config.getContextPath()+"/products/" + String.valueOf(product.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", false);
            return "product-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
 
    @GetMapping(value = {"/products/{productId}/delete"})
    public String showDeleteProductById(Model model, @PathVariable long productId) {
        Product product = null;
        try {
            product = productService.findById(productId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("product", product);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "product";
    }
     
    @PostMapping(value = {"/products/{productId}/delete"})
    public String deleteProductById(Model model, @PathVariable long productId) {
        try {
            productService.deleteById(productId);
            return "redirect:"+config.getContextPath()+"/products";
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "product";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
    
}
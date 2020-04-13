package com.elwan.microservices.eshopservice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.elwan.microservices.eshopservice.config.Configuration;
import com.elwan.microservices.eshopservice.dto.Customer;
import com.elwan.microservices.eshopservice.dto.Product;
import com.elwan.microservices.eshopservice.dto.ShoppingCart;
import com.elwan.microservices.eshopservice.service.EshopService;
 
@Controller
public class EshopController {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private EshopService eshopService;
    
    @Autowired
    private Configuration config;
     
    @GetMapping(value = {"/", "/index"})
    public String index(Model model, HttpSession session) {
    	logger.info("findProductById just started ...");
    	
    	session.setAttribute(Customer.TAG, new Customer());
    	session.setAttribute("title", config.getAppTitle());
    	session.setAttribute("contextPath", config.getContextPath());
    	session.setAttribute("productContextPath", config.getProductContextPath());
    	session.setAttribute("customerContextPath", config.getCustomerContextPath());
    	session.setAttribute("orderContextPath", config.getOrderContextPath());
    	
    	logger.info("findProductById finished successfully");
        return "index";
    }
    
    @PostMapping(value = {"/cart/add/{productId}"})
    public String addProductToCart(Model model, @PathVariable long productId, HttpSession session) {
    	logger.info("addProductToCart just started ...");

    	Customer c = (Customer) session.getAttribute(Customer.TAG);
    	
    	eshopService.addProductToCart(c.getId(), productId);
    	
    	model.addAttribute("infoMsg", String.format("Product added successfully"));
    	
    	logger.info("addProductToCart finished successfully");
    	return "products";
    }
    
    @PostMapping(value = {"/customers/email"})
    public String findCustomer(Model model, @ModelAttribute("customer") Customer customer, HttpSession session) {
    	logger.info("findCustomer just started ...");
    	
    	Customer c = eshopService.findCustomerByEmail(customer.getEmail());
    	session.setAttribute(Customer.TAG, c);
    	
    	fetchProducts(model, 1, config.getProductRowsPerPage(), session);
    	
    	logger.info("findCustomer finished successfully");
    	return "products";
    }
 
    @GetMapping(value = "/products/all")
    public String getProducts(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber, HttpSession session) {
    	logger.info("getProducts just started ...");

    	fetchProducts(model, pageNumber, config.getProductRowsPerPage(), session);
    	
    	logger.info("getProducts finished successfully");
        return "products";
    }
    
    private void fetchProducts(Model model, int from, int to, HttpSession session) {
    	logger.info("fetchProducts just started ...");
    	
    	List<Product> products = eshopService.findProducts(from, to);
     
        long count = eshopService.getProductsCount();
        boolean hasPrev = from > 1;
        boolean hasNext = (from * config.getProductRowsPerPage()) < count;
        
        session.setAttribute(Product.TAG_MANY, products);
        session.setAttribute("hasPrev", hasPrev);
        session.setAttribute("prev", from - 1);
        session.setAttribute("hasNext", hasNext);
        session.setAttribute("next", from + 1);
        
        logger.info("fetchProducts finished successfully");
    }
 
    @GetMapping(value = "/show/cart/customer/{customerId}")
    public String getCustomerCart(Model model, @PathVariable String customerId, HttpSession session) {
    	logger.info("getCustomerCart just started ...");

    	ShoppingCart cart = eshopService.getCustomerCart(customerId);
    	model.addAttribute("cart", cart);
    	
    	logger.info("getCustomerCart finished successfully");
        return "customer-cart";
    }
    
    @PostMapping(value = "/cart/{cartId}/cartitem/{cartItemId}/delete")
    public String deleteShoppingCartItem(Model model, @PathVariable Long cartId, @PathVariable Long cartItemId) {
    	logger.info("deleteShoppingCartItem just started ...");

    	ShoppingCart cart = eshopService.deleteCartItem(cartId, cartItemId);
    	model.addAttribute("cart", cart);
    	
    	logger.info("deleteShoppingCartItem finished successfully");
        return "customer-cart";
    }
}
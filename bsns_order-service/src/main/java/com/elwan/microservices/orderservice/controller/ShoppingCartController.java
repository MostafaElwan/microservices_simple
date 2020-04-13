package com.elwan.microservices.orderservice.controller;

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

import com.elwan.microservices.orderservice.config.Configuration;
import com.elwan.microservices.orderservice.domain.ShoppingCart;
import com.elwan.microservices.orderservice.domain.ShoppingCartItem;
import com.elwan.microservices.orderservice.service.ShoppingCartService;
 
@Controller
public class ShoppingCartController {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    private Configuration config;
     
    @GetMapping(value = "/shoppingCarts")
    public String getShoppingCarts(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
    	
        List<ShoppingCart> shoppingCarts = shoppingCartService.findAll(pageNumber, config.getRowsPerPage());
     
        long count = shoppingCartService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
        model.addAttribute("shoppingCarts", shoppingCarts);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "shopping-cart-list";
    }
    
    @GetMapping(value = "/shoppingCarts/{shoppingCartId}")
    public String getShoppingCartById(Model model, @PathVariable long shoppingCartId) {
        ShoppingCart shoppingCart = null;
        try {
            shoppingCart = shoppingCartService.findById(shoppingCartId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("shoppingCart", shoppingCart);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "shopping-cart";
    }
    
    @GetMapping(value = {"/shoppingCarts/{shoppingCartId}/details"})
    public String showDetailsShoppingCartById(Model model, @PathVariable long shoppingCartId) {
    	
    	ShoppingCart shoppingCart = shoppingCartService.findById(shoppingCartId);
    	List<ShoppingCartItem> items = shoppingCart.getItems();
    	model.addAttribute("items", items);
    	
    	
    	
    	
        model.addAttribute("contextPath", config.getContextPath());
        return "shopping-cart-items-list";
    }
    
    @PostMapping(value = {"/shoppingCarts/{shoppingCartId}/details/{shoppingCartItemId}/delete"})
    public String deleteShoppingCartItemById(Model model, @PathVariable long shoppingCartId, @PathVariable long shoppingCartItemId) {
        try {
        	ShoppingCart cart = shoppingCartService.findById(shoppingCartId);
            boolean isLastItem = shoppingCartService.deleteProduct(cart, shoppingCartItemId);
            if(isLastItem)
            	return "redirect:"+config.getContextPath()+"/shoppingCarts/";
            	
            return "redirect:"+config.getContextPath()+"/shoppingCarts/" + shoppingCartId + "/details";
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "shopping-cart";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
    
    @GetMapping(value = {"/shoppingCarts/add"})
    public String showAddShoppingCart(Model model) {
        
        ShoppingCart shoppingCart = new ShoppingCart();
        model.addAttribute("add", true);
        model.addAttribute("shoppingCart", shoppingCart);
     
        model.addAttribute("contextPath", config.getContextPath());
        return "shopping-cart-edit";
    }
     
    @PostMapping(value = "/shoppingCarts/add")
    public String addShoppingCart(Model model, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {        
        try {
            ShoppingCart newShoppingCart = shoppingCartService.save(shoppingCart);
            
            return "redirect:"+config.getContextPath()+"/shoppingCarts/" + String.valueOf(newShoppingCart.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            
            model.addAttribute("add", true);
            return "shopping-cart-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
     
    @GetMapping(value = {"/shoppingCarts/{shoppingCartId}/edit"})
    public String showEditShoppingCart(Model model, @PathVariable long shoppingCartId) {
        ShoppingCart shoppingCart = null;
        try {
            shoppingCart = shoppingCartService.findById(shoppingCartId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("shoppingCart", shoppingCart);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "shopping-cart-edit";
    }
     
    @PostMapping(value = {"/shoppingCarts/{shoppingCartId}/edit"})
    public String updateShoppingCart(Model model, @PathVariable long shoppingCartId, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
        try {
            shoppingCart.setId(shoppingCartId);
            shoppingCartService.update(shoppingCart);
            return "redirect:"+config.getContextPath()+"/shoppingCarts/" + String.valueOf(shoppingCart.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", false);
            return "shopping-cart-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
 
    @GetMapping(value = {"/shoppingCarts/{shoppingCartId}/delete"})
    public String showDeleteShoppingCartById(Model model, @PathVariable long shoppingCartId) {
        ShoppingCart shoppingCart = null;
        try {
            shoppingCart = shoppingCartService.findById(shoppingCartId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("shoppingCart", shoppingCart);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "shopping-cart";
    }
     
    @PostMapping(value = {"/shoppingCarts/{shoppingCartId}/delete"})
    public String deleteShoppingCartById(Model model, @PathVariable long shoppingCartId) {
        try {
            shoppingCartService.deleteById(shoppingCartId);
            return "redirect:"+config.getContextPath()+"/shoppingCarts";
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "shopping-cart";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
    
}
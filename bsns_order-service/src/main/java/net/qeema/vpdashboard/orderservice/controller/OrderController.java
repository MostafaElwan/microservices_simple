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
import com.elwan.microservices.orderservice.domain.Order;
import com.elwan.microservices.orderservice.service.OrderService;
 
@Controller
public class OrderController {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private Configuration config;
     
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", config.getAppTitle());
        model.addAttribute("contextPath", config.getContextPath());
        return "index";
    }
 
    @GetMapping(value = "/orders")
    public String getOrders(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
    	
        List<Order> orders = orderService.findAll(pageNumber, config.getRowsPerPage());
     
        long count = orderService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
        model.addAttribute("orders", orders);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "order-list";
    }
    
    @GetMapping(value = "/orders/{orderId}")
    public String getOrderById(Model model, @PathVariable long orderId) {
        Order order = null;
        try {
            order = orderService.findById(orderId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("order", order);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "order";
    }
 
    @GetMapping(value = {"/orders/add"})
    public String showAddOrder(Model model) {
        
        Order order = new Order();
        model.addAttribute("add", true);
        model.addAttribute("order", order);
     
        model.addAttribute("contextPath", config.getContextPath());
        return "order-edit";
    }
     
    @PostMapping(value = "/orders/add")
    public String addOrder(Model model, @ModelAttribute("order") Order order) {        
        try {
            Order newOrder = orderService.save(order);
            
            return "redirect:"+config.getContextPath()+"/orders/" + String.valueOf(newOrder.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            
            model.addAttribute("add", true);
            return "order-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
     
    @GetMapping(value = {"/orders/{orderId}/edit"})
    public String showEditOrder(Model model, @PathVariable long orderId) {
        Order order = null;
        try {
            order = orderService.findById(orderId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("order", order);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "order-edit";
    }
     
    @PostMapping(value = {"/orders/{orderId}/edit"})
    public String updateOrder(Model model, @PathVariable long orderId, @ModelAttribute("order") Order order) {
        try {
            order.setId(orderId);
            orderService.update(order);
            return "redirect:"+config.getContextPath()+"/orders/" + String.valueOf(order.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", false);
            return "order-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
 
    @GetMapping(value = {"/orders/{orderId}/delete"})
    public String showDeleteOrderById(Model model, @PathVariable long orderId) {
        Order order = null;
        try {
            order = orderService.findById(orderId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("order", order);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "order";
    }
     
    @PostMapping(value = {"/orders/{orderId}/delete"})
    public String deleteOrderById(Model model, @PathVariable long orderId) {
        try {
            orderService.deleteById(orderId);
            return "redirect:"+config.getContextPath()+"/orders";
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "order";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
    
}
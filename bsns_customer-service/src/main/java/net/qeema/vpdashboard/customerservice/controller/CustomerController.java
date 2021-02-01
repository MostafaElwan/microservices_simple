package com.elwan.microservices.customerservice.controller;

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

import com.elwan.microservices.customerservice.config.Configuration;
import com.elwan.microservices.customerservice.domain.Customer;
import com.elwan.microservices.customerservice.domain.Segment;
import com.elwan.microservices.customerservice.service.CustomerService;
import com.elwan.microservices.customerservice.service.SegmentService;
 
@Controller
public class CustomerController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Configuration config;
 
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private SegmentService segmentService;
    
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", config.getAppTitle());
        model.addAttribute("contextPath", config.getContextPath());
        return "index";
    }
 
    @GetMapping(value = "/customers")
    public String getCustomers(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
    	
        List<Customer> customers = customerService.findAll(pageNumber, config.getRowsPerPage());
     
        long count = customerService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
        model.addAttribute("customers", customers);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "customer-list";
    }
    
    @GetMapping(value = {"/customers/segment/{segmentId}"})
    public String showSegmentCustomers(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber, @PathVariable String segmentId) {
    	
    	Segment segment = segmentService.findById(segmentId);
    	List<Customer> customers = segment.getCustomers();
    	
    	long count = customers.size();
    	boolean hasPrev = pageNumber > 1;
    	boolean hasNext = (pageNumber * config.getRowsPerPage()) < count;
    	model.addAttribute("customers", customers);
    	model.addAttribute("hasPrev", hasPrev);
    	model.addAttribute("prev", pageNumber - 1);
    	model.addAttribute("hasNext", hasNext);
    	model.addAttribute("next", pageNumber + 1);
    	
    	model.addAttribute("contextPath", config.getContextPath());
    	return "customer-list";
    }
 
    @GetMapping(value = "/customers/{customerId}")
    public String getCustomerById(Model model, @PathVariable String customerId) {
        Customer customer = null;
        try {
            customer = customerService.findById(customerId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("customer", customer);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "customer";
    }
 
    @GetMapping(value = {"/customers/add"})
    public String showAddCustomer(Model model) {
    	
    	List<Segment> segments = segmentService.findAll(1, 100);
        model.addAttribute("segments", segments);
        
        Customer customer = new Customer();
        model.addAttribute("add", true);
        model.addAttribute("customer", customer);
     
        model.addAttribute("contextPath", config.getContextPath());
        return "customer-edit";
    }
     
    @PostMapping(value = "/customers/add")
    public String addCustomer(Model model, @RequestParam("selectedSegment") String selectedSegmentId, @ModelAttribute("customer") Customer customer) {        
        try {
        	Segment selectedSegment = segmentService.findById(selectedSegmentId);
        	
        	customer.setSegment(selectedSegment);
            
            Customer newCustomer = customerService.save(customer);
            segmentService.update(selectedSegment);
            
            return "redirect:"+config.getContextPath()+"/customers/" + String.valueOf(newCustomer.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            
            List<Segment> segments = segmentService.findAll(1, 100);
            model.addAttribute("segments", segments);
     
            model.addAttribute("add", true);
            return "customer-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}        
    }
     
    @GetMapping(value = {"/customers/{customerId}/edit"})
    public String showEditCustomer(Model model, @PathVariable String customerId) {
        Customer customer = null;
        try {
        	List<Segment> segments = segmentService.findAll(1, 100);
            model.addAttribute("segments", segments);
            
            customer = customerService.findById(customerId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("customer", customer);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "customer-edit";
    }
     
    @PostMapping(value = {"/customers/{customerId}/edit"})
    public String updateCustomer(Model model, @RequestParam("selectedSegment") String selectedSegmentId, @PathVariable String customerId, @ModelAttribute("customer") Customer updatedCustomer) {
        try {
        	Customer origin = customerService.findById(customerId);
        	updatedCustomer.fill(origin);
        	
        	Segment selectedSegment = segmentService.findById(selectedSegmentId);
        	Segment oldSegment = updatedCustomer.setSegment(selectedSegment);
            
            customerService.update(updatedCustomer);
            segmentService.update(selectedSegment);
            segmentService.update(oldSegment);
            return "redirect:"+config.getContextPath()+"/customers/" + String.valueOf(updatedCustomer.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();            
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", false);
            return "customer-edit";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
 
    @GetMapping(value = {"/customers/{customerId}/delete"})
    public String showDeleteCustomerById(Model model, @PathVariable String customerId) {
        Customer customer = null;
        try {
            customer = customerService.findById(customerId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("customer", customer);
        
        model.addAttribute("contextPath", config.getContextPath());
        return "customer";
    }
     
    @PostMapping(value = {"/customers/{customerId}/delete"})
    public String deleteCustomerById(Model model, @PathVariable String customerId) {
        try {
        	Customer c = customerService.findById(customerId);
        	Segment s = c.getSegment();
        	
        	s.getCustomers().remove(c);
        	
            customerService.deleteById(customerId);
            segmentService.update(s);
            
            return "redirect:"+config.getContextPath()+"/customers";
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "customer";
        } finally {
        	model.addAttribute("contextPath", config.getContextPath());
		}
    }
 
}
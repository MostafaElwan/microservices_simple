package com.elwan.microservices.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.elwan.microservices.orderservice.client.CustomerClient;
import com.elwan.microservices.orderservice.client.ProductClient;
import com.elwan.microservices.orderservice.domain.ShoppingCart;
import com.elwan.microservices.orderservice.domain.ShoppingCartItem;
import com.elwan.microservices.orderservice.dto.Customer;
import com.elwan.microservices.orderservice.dto.Product;
import com.elwan.microservices.orderservice.repository.ShoppingCartItemRepository;
import com.elwan.microservices.orderservice.repository.ShoppingCartRepository;
 
@Service
public class ShoppingCartService {
    
    @Autowired
    private ShoppingCartRepository cartRepository;
    
    @Autowired
    private ShoppingCartItemRepository cartItemRepository;
    
    @Autowired
    private CustomerClient customerClient;
    
    @Autowired
    private ProductClient productClient;
    
    private boolean existsById(Long id) {
        return cartRepository.existsById(id);
    }
    
    public ShoppingCart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }
    
    public ShoppingCart findByCustomerId(String customerId) {
        return cartRepository.findByCustomerId(customerId).orElse(null);
    }
    
    public List<ShoppingCart> findAll(int pageNumber, int rowPerPage) {
        List<ShoppingCart> carts = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("createdAt").descending());
        cartRepository.findAll(sortedByLastUpdateDesc).forEach(carts::add);
        return carts;
    }
    
    public ShoppingCart save(ShoppingCart cart) throws Exception {
        return cartRepository.save(cart);
    }
    
    public void update(ShoppingCart cart) throws Exception {
        if (!existsById(cart.getId())) 
            throw new Exception("Cannot find a cart with id: " + cart.getId());

        cartRepository.save(cart);
    }
    
    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) 
            throw new Exception("Cannot find a cart with id: " + id);
            
        cartRepository.deleteById(id);
    }
    
    public Long count() {
        return cartRepository.count();
    }

	public ShoppingCart create(String customerId) throws Exception {
		Customer customer = customerClient.findCustomerById(customerId);
		ShoppingCart cart = new ShoppingCart(customer.getId());
		save(cart);
		
		return cart;
	}

	public void addProduct(ShoppingCart cart, Long productId) throws Exception {
		ShoppingCartItem item = new ShoppingCartItem(cart, productId);
		cartItemRepository.save(item);
	}
	
	public boolean deleteProduct(ShoppingCart cart, Long itemId) throws Exception {
		cartItemRepository.delete(new ShoppingCartItem(itemId));
		
		List<ShoppingCartItem> items = cart.getItems();
		if(items.isEmpty()) {
			deleteById(cart.getId());
			return true;
		}
		return false;
	}

	public void deleteItem(Long itemId) {
		cartItemRepository.delete(new ShoppingCartItem(itemId));
	}
}
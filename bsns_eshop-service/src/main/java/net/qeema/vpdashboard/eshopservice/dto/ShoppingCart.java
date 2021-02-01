package com.elwan.microservices.eshopservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart implements Serializable {
 
    private static final long serialVersionUID = 5057388942388599423L;
    
    public static final ShoppingCart NA = new ShoppingCart(-1L, null, new ArrayList<ShoppingCartItem>(), null, null);
 
    private Long id;
 
    private String customerId;
    
    private List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
    
    private Date createdAt;
 
    private Date updatedAt;
 
}
package com.elwan.microservices.eshopservice.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ShoppingCartItem implements Serializable {
 
    private static final long serialVersionUID = 5057388942388599423L;
 
    private Long id;
 
    private Long productId;
    
    private Date createdAt;
 
    private Date updatedAt;
    
}
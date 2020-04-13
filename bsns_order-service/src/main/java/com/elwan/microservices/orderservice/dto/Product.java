package com.elwan.microservices.orderservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	
    private static final long serialVersionUID = 5057388942388599423L;
    
    public static final Product NA = new Product(-1L, "Not available", BigDecimal.ZERO, "");
 
    private Long id;
 
    private String name;
    
    private BigDecimal price;
 
    private String description;
    
    public String getShortDescription() {
        return StringUtils.left(this.description, 200);
    }

}
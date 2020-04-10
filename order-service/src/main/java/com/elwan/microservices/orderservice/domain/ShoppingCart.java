package com.elwan.microservices.orderservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Validated
@Entity
@Table(name = "shopping_carts")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class ShoppingCart implements Serializable {
 
    private static final long serialVersionUID = 5057388942388599423L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String customerId;
    
    @OneToMany(targetEntity = ShoppingCartItem.class, 
    		mappedBy = "cart", 
    		orphanRemoval = false, 
    		fetch = FetchType.LAZY)
    private List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
    
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
 
    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;
 
    public ShoppingCart(String customerId) {
    	this.customerId = customerId;
    }
}
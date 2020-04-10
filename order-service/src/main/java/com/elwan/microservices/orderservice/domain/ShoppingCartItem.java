package com.elwan.microservices.orderservice.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Validated
@Entity
@Table(name = "shopping_cart_items")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class ShoppingCartItem implements Serializable {
 
    private static final long serialVersionUID = 5057388942388599423L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private Long productId;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
	@Fetch(FetchMode.JOIN)
    private ShoppingCart cart;
    
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
 
    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;
    
    public ShoppingCartItem(Long id) {
    	this.id = id;
    }
 
    public ShoppingCartItem(ShoppingCart cart, Long productId) {
    	this.cart = cart;
    	this.productId = productId;
    }
}
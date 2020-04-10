package com.elwan.microservices.orderservice.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
 
@Validated
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Order implements Serializable {
 
    private static final long serialVersionUID = 5057388942388599423L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String status = "Pending";
    
    @Column(length = 4000)
    private String comment;
 
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
 
    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;
    
    public String getShortComment() {
        return StringUtils.left(this.comment, 200);
    }
 
}
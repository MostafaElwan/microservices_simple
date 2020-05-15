package com.elwan.microservices.productservice.domain;

import java.io.Serializable;
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
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
 
@Validated
@Entity
@Table(name = "categories")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Category implements Serializable {
 
    private static final long serialVersionUID = 5057388942383459423L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    private String name;
    
    @Column(length = 200)
    private String description;
 
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
 
    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;
 
    @JsonIgnore
    @OneToMany(targetEntity = Product.class, 
    		mappedBy = "category", 
    		orphanRemoval = false, 
    		fetch = FetchType.LAZY)
    private List<Product> products;
    
    public String getShortDescription() {
        return StringUtils.left(this.description, 20);
    }

}
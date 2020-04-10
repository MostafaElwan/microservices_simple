package com.elwan.microservices.productservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
 
@Validated
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Product implements Serializable {
 
    private static final long serialVersionUID = 5057388942388599423L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    private String name;
    
    @NotNull
    private BigDecimal price;
 
    @Column(length = 200)
    private String description;
 
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
 
    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
    private Category category;
    
    public String getShortDescription() {
        return StringUtils.left(this.description, 200);
    }
	
	public Long getCategoryId() {
		if(category == null)
			return -1L;
		
		return category.getId();
	}
}
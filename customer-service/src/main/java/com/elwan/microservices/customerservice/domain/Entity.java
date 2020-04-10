package com.elwan.microservices.customerservice.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public abstract class Entity  implements Fillable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@CreatedDate
	private Date createdAt;

	@LastModifiedDate
	private Date updatedAt;
	
	@Override
	public void fill(Fillable fillable) {
		Entity origin = (Entity) fillable;
		
		if(id == null)
			id = origin.getId();
		
		if(createdAt == null)
			createdAt = origin.getCreatedAt();
		
		if(updatedAt == null)
			updatedAt = origin.getUpdatedAt();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Entity))
			return false;
		
		return ((Entity)obj).getId().equals(getId());
	}
}

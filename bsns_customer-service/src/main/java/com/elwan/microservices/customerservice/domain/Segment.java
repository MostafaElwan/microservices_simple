package com.elwan.microservices.customerservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@Document(collection = "segments")
@Data
@NoArgsConstructor
public class Segment extends Entity {
 
    private static final long serialVersionUID = 5057388942388599423L;
 
    @NotBlank
    private String name;
    
    private String description;
    
    @DBRef
    private List<Customer> customers = new ArrayList<Customer>();
    
    public Segment(String id) {
    	setId(id);
    }
    
    public Segment(String id, String name) {
    	setId(id);
    	this.name = name;
    }
    
    public String getShortDescription() {
        return StringUtils.left(this.description, 20);
    }
 
	@Override
	public void fill(Fillable fillable) {
		super.fill(fillable);
		
		Segment origin = (Segment) fillable;
		
		if(name == null)
			name = origin.getName();
		
		if(description == null)
			description = origin.getDescription();
		
	}
}
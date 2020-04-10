package com.elwan.microservices.customerservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Validated
@Document(collection = "customers")
@Data
public class Customer extends Entity {
 
    private static final long serialVersionUID = 5057388942388599423L;
 
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    private String email;
    
    @NotNull
    private Integer age;
    
    private String address;
    
    @DBRef
    private Segment segment;
	
	public Segment setSegment(Segment segment) {
		Segment oldSegment = null;
		if(this.segment != null) {
			oldSegment = this.segment;
			this.segment.getCustomers().remove(this);
		}
		
		this.segment = segment;
		this.segment.getCustomers().add(this);
		
		return oldSegment;
	}
	
	public String getSegmentId() {
		if(segment == null)
			return "";
		
		return segment.getId();
	}
	
	@Override
	public void fill(Fillable fillable) {
		super.fill(fillable);
		
		Customer origin = (Customer)fillable;
		
		if(firstName == null)
			firstName = origin.getFirstName();
		
		if(lastName == null)
			lastName = origin.getLastName();
		
		if(email == null)
			email = origin.getEmail();
		
		if(age == null)
			age = origin.getAge();
		
		if(address == null)
			address = origin.getAddress();
		
		if(segment == null)
			segment = origin.getSegment();
		
	}

}
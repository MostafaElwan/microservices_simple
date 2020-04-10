package com.elwan.microservices.orderservice.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 5057388983748599423L;

	public static final String TAG = "customer";
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	public String getName() {
		return String.format("%s %s", firstName, lastName);
	}
	
}

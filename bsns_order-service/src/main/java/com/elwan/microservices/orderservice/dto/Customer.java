package com.elwan.microservices.orderservice.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 5057388983748599423L;
	
	public static final Customer NA = new Customer(null, "Not available", "", "Not deteremined !");

	public static final String TAG = "customer";
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	public String getName() {
		return String.format("%s %s", firstName, lastName);
	}
	
}

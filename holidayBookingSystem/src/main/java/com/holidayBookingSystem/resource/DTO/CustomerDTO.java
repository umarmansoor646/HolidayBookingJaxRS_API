package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Customer;

public class CustomerDTO {

	private int id;
	private String name;
	private String email;
	
	public CustomerDTO() {
		
	}
	
	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

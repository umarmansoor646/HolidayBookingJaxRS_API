package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Booking;

public class BookingWithCustomerDTO extends BookingDTO{

	private CustomerDTO customer;

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	public BookingWithCustomerDTO(Booking booking) {
		super(booking);
	}
	public BookingWithCustomerDTO() {
		
	}
}

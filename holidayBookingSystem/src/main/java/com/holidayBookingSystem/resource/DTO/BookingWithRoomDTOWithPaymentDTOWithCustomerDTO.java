package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Booking;

public class BookingWithRoomDTOWithPaymentDTOWithCustomerDTO extends BookingWithRoomDTOWithPaymentDTO{

	public BookingWithRoomDTOWithPaymentDTOWithCustomerDTO(Booking booking) {
		super(booking);
	}
	public BookingWithRoomDTOWithPaymentDTOWithCustomerDTO() {}

	private CustomerDTO customer;

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
}

package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.PaymentMethod;

public class PaymentWithCustomerDTOWithBookingWithRoomDTO extends PaymentWithCustomerDTO{
	
	private BookingWithRoomDTO booking;
	
	public PaymentWithCustomerDTOWithBookingWithRoomDTO() {}
	
	public PaymentWithCustomerDTOWithBookingWithRoomDTO(PaymentMethod payment) {
		super(payment);
		// TODO Auto-generated constructor stub
	}

	public BookingWithRoomDTO getBooking() {
		return booking;
	}

	public void setBooking(BookingWithRoomDTO booking) {
		this.booking = booking;
	}



	
}

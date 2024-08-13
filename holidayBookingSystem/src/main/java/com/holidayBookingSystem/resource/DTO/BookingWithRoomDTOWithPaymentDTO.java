package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Booking;

public class BookingWithRoomDTOWithPaymentDTO extends BookingWithRoomDTO {

	private PaymentDTO payment;
	
	public BookingWithRoomDTOWithPaymentDTO() {}
	
	public BookingWithRoomDTOWithPaymentDTO(Booking booking) {
		super(booking);
		
		this.payment = booking.getPayment() != null ? new PaymentDTO(booking.getPayment()) : null;
	}

	public PaymentDTO getPayment() {
		return payment;
	}
	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}
}

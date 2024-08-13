package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Booking;

public class BookingWithCustomerDTOWithPaymentDTO extends BookingWithCustomerDTO{
	private PaymentDTO payment;
	
	public BookingWithCustomerDTOWithPaymentDTO() {
		
	}
	
	public BookingWithCustomerDTOWithPaymentDTO(Booking booking) {
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

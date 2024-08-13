package com.holidayBookingSystem.resource.DAO;

public class DebitCardPayment extends PaymentMethod {
	private final String paymentMethod = "Debit Card";

	@Override
	public String getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public boolean processPayment() {
		return true;
		
	}

}

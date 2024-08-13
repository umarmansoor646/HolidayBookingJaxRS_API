package com.holidayBookingSystem.resource.DAO;

public class CreditCardPayment extends PaymentMethod {
	private final String paymentMethod = "Credit Card";
	
	@Override
	public String getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public boolean processPayment() {
		return true;
	}
}

package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.PaymentMethod;

public class PaymentDTO {
	
	private int id;
	private double amount;
	private String paymentDate;
	private String paymentMethod;
	
	public PaymentDTO() {}
	
	public PaymentDTO(PaymentMethod payment) {
		if (payment != null) {
			this.id = payment.getId();
			this.amount = payment.getAmount();
			this.paymentDate = payment.getPaymentDate().toString();
			this.paymentMethod = payment.getPaymentMethod();
		} 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
}

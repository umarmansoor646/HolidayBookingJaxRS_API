package com.holidayBookingSystem.resource.DAO;

import java.sql.Date;

public abstract class PaymentMethod {
	
	private int id;
	private double amount;
	private Date paymentDate;
	private String paymentMethod;
	private int CustomerID;
	private int BookingID;
	
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
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public void setPaymentMethod() {
		this.paymentMethod = this.getPaymentMethod();
	}
	
	
	
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getBookingID() {
		return BookingID;
	}
	public void setBookingID(int bookingID) {
		BookingID = bookingID;
	}
	public abstract String getPaymentMethod();
	public abstract boolean processPayment();
	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", amount=" + amount + ", paymentDate=" + paymentDate + ", paymentMethod="
				+ paymentMethod + ", CustomerID=" + CustomerID + ", BookingID=" + BookingID + "]";
	}

	
}

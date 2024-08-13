package com.holidayBookingSystem.resource.DAO;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private int id;
	private String name;
	private String email;
	private List<Booking> bookings = new ArrayList<>();
	private List<Integer> payments = new ArrayList<>();
	
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
	public List<Booking> getBookings() {
		return bookings;
	}
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}
	
	public boolean removeBooking(Booking booking) {
		return bookings.remove(booking);
	}
	public List<Integer> getPayments() {
		return payments;
	}
	
	public void addPayment(int paymentID) {
		this.payments.add(paymentID);
	}
	
	public boolean removePayment(Integer paymentID) {
		return payments.remove(paymentID);
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", bookings=" + bookings + ", payments="
				+ payments + "]";
	}
	
	

}

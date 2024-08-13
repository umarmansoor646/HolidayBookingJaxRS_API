package com.holidayBookingSystem.resource.DAO;

import java.sql.Date;

public class Booking {
	
	private int id;
	private Date startDate;
	private Date endDate;
	private Room room;
	private PaymentMethod payment;
	private int CustomerID;
	
	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public Booking() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public PaymentMethod getPayment() {
		return payment;
	}

	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", room=" + room
				+ ", payment=" + payment + ", CustomerID=" + CustomerID + "]";
	}
	

	
	
	
	
}

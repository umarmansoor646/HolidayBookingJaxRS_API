package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Booking;

public class BookingDTO {

	private int id;
	private String startDate;
	private String endDate;
	
	public BookingDTO() {
		
	}
	
	public BookingDTO (Booking booking) {
		this.id = booking.getId();
		this.startDate = booking.getStartDate().toString();
		this.endDate = booking.getEndDate().toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	
	
}

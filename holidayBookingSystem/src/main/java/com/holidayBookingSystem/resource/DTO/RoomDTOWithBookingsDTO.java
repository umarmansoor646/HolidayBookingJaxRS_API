package com.holidayBookingSystem.resource.DTO;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Room;

public class RoomDTOWithBookingsDTO extends RoomWithHotelDTO{
	
	public RoomDTOWithBookingsDTO() {
		
	}
	
	public RoomDTOWithBookingsDTO(Room r) {
		super(r);
	}

	private List<BookingWithCustomerDTOWithPaymentDTO> bookings = new ArrayList<>();

	public List<BookingWithCustomerDTOWithPaymentDTO> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingWithCustomerDTOWithPaymentDTO> bookings) {
		this.bookings = bookings;
	}
	
	
}

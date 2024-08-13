package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Booking;

public class BookingWithRoomDTO extends BookingDTO {

	private RoomWithHotelDTO room;
	
	public BookingWithRoomDTO() {
		
	}
	
	public BookingWithRoomDTO(Booking booking) {
		super(booking);
		this.room = booking.getRoom() != null ? new RoomWithHotelDTO(booking.getRoom()) : null;
	}
	
	public RoomWithHotelDTO getRoomWithHotel() {
		return room;
	}
	
	public void setRoomWithHotel(RoomWithHotelDTO room) {
		this.room = room;
	}
	

}

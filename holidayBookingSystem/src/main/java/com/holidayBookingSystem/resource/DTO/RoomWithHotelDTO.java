package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Room;

public class RoomWithHotelDTO extends RoomDTO {
	
	private HotelDTO hotel;
	
	public RoomWithHotelDTO() {}
	
	public RoomWithHotelDTO(Room r) {
		super(r);
		hotel = null;
	}

	public HotelDTO getHotelDTO() {
		return hotel;
	}

	public void setHotelDTO(HotelDTO hotel) {
		this.hotel = hotel;
	}
	
}

package com.holidayBookingSystem.resource.DTO;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Hotel;
import com.holidayBookingSystem.resource.DAO.Room;

public class HotelWithRoomDTO extends HotelDTO {
	
	private List<RoomDTO> rooms = new ArrayList<>();
	
	public HotelWithRoomDTO() {
		
	}
	
	public HotelWithRoomDTO (Hotel hotel) {
		
		super(hotel);
		
		for (Room room : hotel.getRooms()) {
			rooms.add(new RoomDTO(room));
		}
	}
	
	public List<RoomDTO> getRooms() {
		return rooms;
	}
}

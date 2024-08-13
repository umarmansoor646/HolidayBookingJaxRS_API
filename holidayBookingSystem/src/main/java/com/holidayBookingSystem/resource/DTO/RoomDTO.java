package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Room;

public class RoomDTO {

	private int id;
    private String roomNumber;
    private String type;
    
    public RoomDTO() {}
    
    public RoomDTO(Room r) {
    	if (r != null) {
	    	this.id = r.getId();
	    	this.roomNumber = r.getRoomNumber();
	    	this.type = r.getType();
    	}
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}


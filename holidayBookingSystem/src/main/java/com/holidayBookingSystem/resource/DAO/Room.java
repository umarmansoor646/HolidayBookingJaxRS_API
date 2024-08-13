package com.holidayBookingSystem.resource.DAO;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private int id;
    private String roomNumber;
    private String type;
    private int hotelID;
    private List<Integer> bookings = new ArrayList<>();
    
    public Room() {}
    
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
	public int getHotel() {
		return hotelID;
	}
	public void setHotel(int hotelID) {
		this.hotelID = hotelID;
	}
	
	public void addBookings(int bookingID) {
		bookings.add(bookingID);
	}	
	
	public boolean removeBooking(Integer bookingID) {
		return bookings.remove(bookingID);
	}
	
	public List<Integer> getBookings(){
		return bookings;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNumber=" + roomNumber + ", type=" + type + ", hotelID=" + hotelID
				+ ", bookings=" + bookings + "]";
	}
    
	
    
}

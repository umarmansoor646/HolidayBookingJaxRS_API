package com.holidayBookingSystem.resource.DAO;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	private int id;
    private String name;
    private String address;
    private List<Room> rooms = new ArrayList<>();
    
    public Hotel() {}

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void addRoom(Room r) {
		rooms.add(r);
	}
	
	public String getName() { 
    	return name; 
    }
    public void setName(String name) { 
    	this.name = name; 
    }

    public String getAddress() { 
    	return address;
    }
    public void setAddress(String address) { 
    	this.address = address;
    }

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", address=" + address + ", rooms=" + rooms + "]";
	} 
    
}

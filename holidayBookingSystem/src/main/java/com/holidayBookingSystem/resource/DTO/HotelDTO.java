package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.Hotel;


public class HotelDTO {
	private int id;
    private String name;
    private String address;
    
    public HotelDTO() {}
    
    public HotelDTO(Hotel hotel) {
    	
    	this.id = hotel.getId();
    	this.name = hotel.getName();
    	this.address = hotel.getAddress();
    }

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

    public String getAddress() { 
    	return address;
    }
    public void setAddress(String address) { 
    	this.address = address;
    }

	@Override
	public String toString() {
		return "HotelDTO [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
    
}

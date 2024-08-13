package com.holidayBookingSystem.resource.DTO;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Booking;
import com.holidayBookingSystem.resource.DAO.Customer;

public class CustomerWithBookingsDTO extends CustomerDTO{
	
	private List<BookingWithRoomDTOWithPaymentDTO> bookings = new ArrayList<>();

	public CustomerWithBookingsDTO(){
		super();
	}
	public CustomerWithBookingsDTO(Customer customer) {
		super(customer);
		
		for (Booking booking : customer.getBookings()) {
			bookings.add(new BookingWithRoomDTOWithPaymentDTO(booking));
		}
	}
	
	public List<BookingWithRoomDTOWithPaymentDTO> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingWithRoomDTOWithPaymentDTO> bookings) {
		this.bookings = bookings;
	}

}

package com.holidayBookingSystem.resource.ApiLayer;

import javax.ws.rs.core.Response;

import com.holidayBookingSystem.resource.ServiceLayer.*;


public interface API<T, K> {
	
	public static Service customerService = new CustomerService();
	public static Service hotelService = new HotelService();
	public static Service paymentService = new PaymentService();
	public static Service bookingService = new BookingService();
	public static Service roomService = new RoomService();
	
    Response getAll();
    Response create(T DTO);
    Response update(K id, T DTO);
    Response delete(K id);
	Response getById(K id);
	
}

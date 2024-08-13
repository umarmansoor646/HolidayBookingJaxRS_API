package com.holidayBookingSystem.resource.util;

import java.sql.Date;

import com.holidayBookingSystem.resource.DAO.Booking;
import com.holidayBookingSystem.resource.DAO.CreditCardPayment;
import com.holidayBookingSystem.resource.DAO.Customer;
import com.holidayBookingSystem.resource.DAO.DebitCardPayment;
import com.holidayBookingSystem.resource.DAO.Hotel;
import com.holidayBookingSystem.resource.DAO.PaymentMethod;
import com.holidayBookingSystem.resource.DAO.Room;
import com.holidayBookingSystem.resource.DTO.BookingWithRoomDTOWithPaymentDTOWithCustomerDTO;
import com.holidayBookingSystem.resource.DTO.CustomerDTO;
import com.holidayBookingSystem.resource.DTO.CustomerWithBookingsDTO;
import com.holidayBookingSystem.resource.DTO.HotelDTO;
import com.holidayBookingSystem.resource.DTO.HotelWithRoomDTO;
import com.holidayBookingSystem.resource.DTO.PaymentWithCustomerDTOWithBookingWithRoomDTO;
import com.holidayBookingSystem.resource.DTO.RoomDTO;
import com.holidayBookingSystem.resource.DTO.RoomDTOWithBookingsDTO;
import com.holidayBookingSystem.resource.DTO.RoomWithHotelDTO;

public class DTO_Converter {
	
	public static HotelDTO HotelToHotelDtoConverter(Hotel hotel) {
		return new HotelDTO(hotel);
	}
	public static CustomerDTO CustomerToCustomerDTOConverter(Customer customer) {
		return new CustomerDTO(customer);
	}
	
	public static HotelWithRoomDTO HotelToHotelWithRoomDtoConverter(Hotel hotel) {
		return new HotelWithRoomDTO(hotel);
	}
	
	public static CustomerWithBookingsDTO CustomerToCustomerWithBookingsDTOConverter(Customer customer) {
		return new CustomerWithBookingsDTO(customer);
	}
	
	public static PaymentWithCustomerDTOWithBookingWithRoomDTO PaymentToPaymentWithCustomerDTOWithBookingWithRoomDTOConverter(PaymentMethod payment) {
		return new PaymentWithCustomerDTOWithBookingWithRoomDTO(payment);
	}
	public static Room RoomDTOToRoomConverter(RoomDTO rDTO) {
		Room r = new Room();
		r.setRoomNumber(rDTO.getRoomNumber());
		r.setType(rDTO.getType());
		return r;
	}
	
	public static Hotel HotelWithRoomDTOTOHotelConverter(HotelWithRoomDTO hotelDTO) {
		Hotel hotel = new Hotel();
		hotel.setName(hotelDTO.getName());
		hotel.setAddress(hotelDTO.getAddress());
		for (RoomDTO room : hotelDTO.getRooms()) {
			hotel.addRoom(RoomDTOToRoomConverter(room));
		}
		
		return hotel;
	}
	
	public static Room RoomDTOWithBookingsDTOToRoomConverter(RoomDTOWithBookingsDTO roomDTO) {
		Room room = new Room();
		if(roomDTO.getHotelDTO() != null)
			room.setHotel(roomDTO.getHotelDTO().getId());
		room.setRoomNumber(roomDTO.getRoomNumber());
		room.setType(roomDTO.getType());
		
		return room;
	}
	
	public static Customer CustomerDTOToCustomerConverter(CustomerWithBookingsDTO customerDTO) {
		Customer customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		return customer;
	}
	
	public static PaymentMethod PaymentDTOToPaymentConverter(PaymentWithCustomerDTOWithBookingWithRoomDTO paymentDTO) {
		PaymentMethod payment;
		if (paymentDTO.getPaymentMethod() != null && paymentDTO.getPaymentMethod().equals("Credit Card"))
			payment = new CreditCardPayment();
		else 
			payment = new DebitCardPayment(); // making default payment by Debit Card
		
		payment.setAmount(paymentDTO.getAmount());
		payment.setPaymentDate(new Date(System.currentTimeMillis()));
		payment.setPaymentMethod();
		if (paymentDTO.getBooking() != null)
			payment.setBookingID(paymentDTO.getBooking().getId());
		return payment;
	}
	
	
	public static Booking BookingDTOToBookingConverter(BookingWithRoomDTOWithPaymentDTOWithCustomerDTO bookingDTO) {
		Booking booking  = new Booking();
		booking.setStartDate(new Date(System.currentTimeMillis()));
		booking.setEndDate(new Date(System.currentTimeMillis()));
		if (bookingDTO.getRoomWithHotel() != null)
			booking.setRoom(DTO_Converter.RoomDTOToRoomConverter(bookingDTO.getRoomWithHotel()));
		if (bookingDTO.getCustomer() != null)
			booking.setCustomerID(bookingDTO.getCustomer().getId());
		
		return booking;
		
	}
	public static Room RoomDTOToRoomConverter(RoomWithHotelDTO roomDTO) {
		Room room = new Room();
		room.setId(roomDTO.getId());
		if(roomDTO.getHotelDTO() != null)
			room.setHotel(roomDTO.getHotelDTO().getId());
		room.setRoomNumber(roomDTO.getRoomNumber());
		room.setType(roomDTO.getType());
		
		return room;
	}
}

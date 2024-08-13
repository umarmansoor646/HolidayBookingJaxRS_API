package com.holidayBookingSystem.resource.ServiceLayer;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Booking;
import com.holidayBookingSystem.resource.DTO.BookingWithRoomDTOWithPaymentDTOWithCustomerDTO;
import com.holidayBookingSystem.resource.DTO.CustomerDTO;
import com.holidayBookingSystem.resource.DTO.HotelDTO;
import com.holidayBookingSystem.resource.util.DTO_Converter;
import com.sun.istack.logging.Logger;

public class BookingService implements Service<BookingWithRoomDTOWithPaymentDTOWithCustomerDTO, Integer, Boolean> {
	
	
	@Override
	public BookingWithRoomDTOWithPaymentDTOWithCustomerDTO findById(Integer id) {
		LOGGER.info("In Service Get Booking with ID : " + id);
		for (Booking booking : dao.getAllBookings()) {
			if (booking.getId() == id ) { 
				BookingWithRoomDTOWithPaymentDTOWithCustomerDTO bookingDTO = new BookingWithRoomDTOWithPaymentDTOWithCustomerDTO(booking);
				int CustomerID = booking.getCustomerID();
				if (booking.getRoom() != null) {
					int HotelID = booking.getRoom().getHotel();
					bookingDTO.getRoomWithHotel().setHotelDTO(new HotelDTO(dao.getHotelByID(HotelID)));
				}
				bookingDTO.setCustomer(new CustomerDTO(dao.getCustomerByID(CustomerID)));
				
				return bookingDTO;
			}
		}
		return null;
	}

	@Override
	public List<BookingWithRoomDTOWithPaymentDTOWithCustomerDTO> findAll() {
		LOGGER.info("In GetALL Bookings Service");
		List<BookingWithRoomDTOWithPaymentDTOWithCustomerDTO> bookingsDTO = new ArrayList<>();
		
		for (Booking booking : dao.getAllBookings()) {
			BookingWithRoomDTOWithPaymentDTOWithCustomerDTO bookingDTO = new BookingWithRoomDTOWithPaymentDTOWithCustomerDTO(booking);
			int CustomerID = booking.getCustomerID();
			if (booking.getRoom() != null) {
				int HotelID = booking.getRoom().getHotel();
				bookingDTO.getRoomWithHotel().setHotelDTO(new HotelDTO(dao.getHotelByID(HotelID)));
			}
			bookingDTO.setCustomer(new CustomerDTO(dao.getCustomerByID(CustomerID)));
			
			
			bookingsDTO.add(bookingDTO);
		}
		
		return bookingsDTO;
	}

	@Override
	public Boolean create(BookingWithRoomDTOWithPaymentDTOWithCustomerDTO entity) {
		LOGGER.info("In Service Create Booking method");
		return dao.createBooking(DTO_Converter.BookingDTOToBookingConverter(entity));
	}

	@Override
	public Boolean update(BookingWithRoomDTOWithPaymentDTOWithCustomerDTO entity, Integer id) {
		LOGGER.info("In Service update Booking id : " + id);
		return dao.updateBooking(DTO_Converter.BookingDTOToBookingConverter(entity), id);
	}

	@Override
	public Boolean delete(Integer id) {
		LOGGER.info("In Service delete booking id : "+ id);
		Booking booking = dao.getBookinByID(id);
		if (booking != null)
			return dao.deletePaymentByID(booking.getPayment().getId()) && dao.deleteBookingByID(id);
		
		return null;
	}

}

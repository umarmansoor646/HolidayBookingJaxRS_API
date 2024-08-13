package com.holidayBookingSystem.resource.ServiceLayer;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Booking;
import com.holidayBookingSystem.resource.DAO.Room;
import com.holidayBookingSystem.resource.DTO.BookingWithCustomerDTOWithPaymentDTO;
import com.holidayBookingSystem.resource.DTO.CustomerDTO;
import com.holidayBookingSystem.resource.DTO.HotelDTO;
import com.holidayBookingSystem.resource.DTO.RoomDTOWithBookingsDTO;
import com.holidayBookingSystem.resource.util.DTO_Converter;

public class RoomService implements Service<RoomDTOWithBookingsDTO, Integer, Boolean>{

	@Override
	public RoomDTOWithBookingsDTO findById(Integer id) {
		LOGGER.info("In Service Get Room with ID : " + id);
		for (Room room : dao.getAllRooms()) {
			
			if (room.getId() == id) {
				RoomDTOWithBookingsDTO roomDTO = new RoomDTOWithBookingsDTO(room);
				roomDTO.setHotelDTO(new HotelDTO(dao.getHotelByID(room.getHotel())));
				
				for (int bookingID : room.getBookings()) {
					Booking booking = dao.getBookinByID(bookingID);
	
					BookingWithCustomerDTOWithPaymentDTO bookingDTO = new BookingWithCustomerDTOWithPaymentDTO(booking);
					bookingDTO.setCustomer(new CustomerDTO(dao.getCustomerByID(booking.getCustomerID())));
					
					roomDTO.getBookings().add(bookingDTO);
				}
				
				return roomDTO;
			}
		}
		return null;
	}

	@Override
	public List<RoomDTOWithBookingsDTO> findAll() {
		LOGGER.info("In GetALL Rooms Service");
		List<RoomDTOWithBookingsDTO> roomsDTO = new ArrayList<>();
		for (Room room : dao.getAllRooms()) {
			RoomDTOWithBookingsDTO roomDTO = new RoomDTOWithBookingsDTO(room);
			roomDTO.setHotelDTO(new HotelDTO(dao.getHotelByID(room.getHotel())));
			
			for (int bookingID : room.getBookings()) {
				Booking booking = dao.getBookinByID(bookingID);
				BookingWithCustomerDTOWithPaymentDTO bookingDTO = new BookingWithCustomerDTOWithPaymentDTO(booking);
				bookingDTO.setCustomer(new CustomerDTO(dao.getCustomerByID(booking.getCustomerID())));
				
				roomDTO.getBookings().add(bookingDTO);
			}
			
			roomsDTO.add(roomDTO);
		}
		
		return roomsDTO;
	}


	@Override
	public Boolean create(RoomDTOWithBookingsDTO entity) {
		LOGGER.info("In Service Create Room method");
		return dao.createRoom(DTO_Converter.RoomDTOWithBookingsDTOToRoomConverter(entity));
	}

	@Override
	public Boolean update(RoomDTOWithBookingsDTO entity, Integer id) {
		LOGGER.info("In Service update Room id : " + id);
		return dao.updateRoom(DTO_Converter.RoomDTOWithBookingsDTOToRoomConverter(entity), id);
	}

	@Override
	public Boolean delete(Integer id) {
		LOGGER.info("In Service Delete Room id : " + id);
		return dao.deleteRoomByID(id);
	}

}

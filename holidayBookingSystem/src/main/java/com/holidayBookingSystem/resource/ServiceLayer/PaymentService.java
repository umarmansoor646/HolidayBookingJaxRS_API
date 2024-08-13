package com.holidayBookingSystem.resource.ServiceLayer;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Booking;
import com.holidayBookingSystem.resource.DAO.PaymentMethod;
import com.holidayBookingSystem.resource.DTO.BookingWithRoomDTO;
import com.holidayBookingSystem.resource.DTO.PaymentWithCustomerDTOWithBookingWithRoomDTO;
import com.holidayBookingSystem.resource.util.DTO_Converter;

public class PaymentService implements Service<PaymentWithCustomerDTOWithBookingWithRoomDTO, Integer, Boolean> {

	@Override
	public PaymentWithCustomerDTOWithBookingWithRoomDTO findById(Integer id) {
		LOGGER.info("In Service Get Payment with ID : " + id);
		for (PaymentMethod payment : dao.getAllPayments()) {
			
			if (payment.getId() == id) {
				PaymentWithCustomerDTOWithBookingWithRoomDTO paymentDTO = DTO_Converter.PaymentToPaymentWithCustomerDTOWithBookingWithRoomDTOConverter(payment);
				int CustomerID = payment.getCustomerID();
				int BookingID = payment.getBookingID();
				paymentDTO.setCustomer(DTO_Converter.CustomerToCustomerDTOConverter(dao.getCustomerByID(CustomerID)));
				Booking booking = dao.getBookinByID(BookingID);
				paymentDTO.setBooking(new BookingWithRoomDTO(booking));
				if (booking.getRoom() != null) {
					int HotelID = booking.getRoom().getHotel();
					paymentDTO.getBooking().getRoomWithHotel().setHotelDTO(DTO_Converter.HotelToHotelDtoConverter(dao.getHotelByID(HotelID)));
				}
				return paymentDTO;
			}
		}
		return null;
	}

	@Override
	public List<PaymentWithCustomerDTOWithBookingWithRoomDTO> findAll() {
		LOGGER.info("In GetALL Payment Service");
		List<PaymentWithCustomerDTOWithBookingWithRoomDTO> paymentsDTO = new ArrayList<>();
		
		for (PaymentMethod payment : dao.getAllPayments()) {
			PaymentWithCustomerDTOWithBookingWithRoomDTO paymentDTO = DTO_Converter.PaymentToPaymentWithCustomerDTOWithBookingWithRoomDTOConverter(payment);
			int CustomerID = payment.getCustomerID();
			int BookingID = payment.getBookingID();
			paymentDTO.setCustomer(DTO_Converter.CustomerToCustomerDTOConverter(dao.getCustomerByID(CustomerID)));
			Booking booking = dao.getBookinByID(BookingID);
			paymentDTO.setBooking(new BookingWithRoomDTO(booking));
			if (booking.getRoom() != null) {
				int HotelID = booking.getRoom().getHotel();
				paymentDTO.getBooking().getRoomWithHotel().setHotelDTO(DTO_Converter.HotelToHotelDtoConverter(dao.getHotelByID(HotelID)));
			}
			paymentsDTO.add(paymentDTO);
		}
		return paymentsDTO;
	}
	
	@Override
	public Boolean create(PaymentWithCustomerDTOWithBookingWithRoomDTO entity) {
		LOGGER.info("In Service Create Payment method");
		return dao.createPayment(DTO_Converter.PaymentDTOToPaymentConverter(entity));
	}

	@Override
	public Boolean update(PaymentWithCustomerDTOWithBookingWithRoomDTO entity, Integer id) {
		LOGGER.info("In Service update Payment id : " + id);
		return dao.updatePayment(DTO_Converter.PaymentDTOToPaymentConverter(entity), id);
	}

	@Override
	public Boolean delete(Integer id) {
		LOGGER.info("In Service Delete Payment id : " + id);
		return dao.deletePaymentByID(id);
	}

}

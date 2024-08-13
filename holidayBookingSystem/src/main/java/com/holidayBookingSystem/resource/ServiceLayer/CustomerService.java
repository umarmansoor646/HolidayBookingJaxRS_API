package com.holidayBookingSystem.resource.ServiceLayer;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Booking;
import com.holidayBookingSystem.resource.DAO.Customer;
import com.holidayBookingSystem.resource.DAO.Hotel;
import com.holidayBookingSystem.resource.DTO.CustomerWithBookingsDTO;
import com.holidayBookingSystem.resource.util.DTO_Converter;

public class CustomerService implements Service<CustomerWithBookingsDTO, Integer, Boolean> {

	@Override
	public CustomerWithBookingsDTO findById(Integer id) {
		LOGGER.info("In Service Get Customer with ID : " + id);
		for (Customer customer : dao.getAllCustomers()) {
			if (customer.getId() == id) {
				CustomerWithBookingsDTO customerDTO = DTO_Converter.CustomerToCustomerWithBookingsDTOConverter(customer);
				
				for (int i = 0 ; i < customer.getBookings().size(); ++i) {
					if (customer.getBookings().get(i).getRoom() != null) {
						int hotelID = customer.getBookings().get(i).getRoom().getHotel();
						Hotel hotel = dao.getHotelByID(hotelID);
						customerDTO.getBookings().get(i).getRoomWithHotel().setHotelDTO(DTO_Converter.HotelToHotelDtoConverter(hotel));
					}
					break;
				}
				return customerDTO;
			}
		}
		return null;
	}

	@Override
	public List<CustomerWithBookingsDTO> findAll() {
		LOGGER.info("In GetALL Customers Service");
		List<CustomerWithBookingsDTO> customersDTO = new ArrayList<>();
		for (Customer customer : dao.getAllCustomers()) {
			CustomerWithBookingsDTO customerDTO = DTO_Converter.CustomerToCustomerWithBookingsDTOConverter(customer);
			for (int i = 0 ; i < customer.getBookings().size(); ++i) {
				if (customer.getBookings().get(i).getRoom() != null) {
					int hotelID = customer.getBookings().get(i).getRoom().getHotel();
					Hotel hotel = dao.getHotelByID(hotelID);
					customerDTO.getBookings().get(i).getRoomWithHotel().setHotelDTO(DTO_Converter.HotelToHotelDtoConverter(hotel));
				}
			}
			customersDTO.add(customerDTO);
		}
		return customersDTO;
	}

	@Override
	public Boolean create(CustomerWithBookingsDTO entity) {
		LOGGER.info("In Service Create Customer method");
		return dao.createCustomer(DTO_Converter.CustomerDTOToCustomerConverter(entity));
	}

	@Override
	public Boolean update(CustomerWithBookingsDTO entity, Integer id) {
		LOGGER.info("In Service update Customer id : " + id);
		return dao.updateCustomer(DTO_Converter.CustomerDTOToCustomerConverter(entity), id);
	}

	@Override
	public Boolean delete(Integer id) {
		LOGGER.info("In Service delete Customer id : "+ id);
		//Before Deleting Customer we have to delete payments and then bookings of customer
		List<Booking> bookings = new ArrayList<>(); // making temporay storage for Bookings because we have to iterate over it to delete
		
		Customer customer = dao.getCustomerByID(id);
		if (customer != null) {
			for (Booking booking : customer.getBookings())
				bookings.add(booking);
			
			for (Booking booking : bookings) {
				dao.deletePaymentByID(booking.getPayment().getId());
				dao.deleteBookingByID(booking.getId());
			}
		}
		// making the temporay bookings null so it will also get garbage collected
		bookings = null;
		
		return dao.deleteCustomerByID(id);
	}

}

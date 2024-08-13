package com.holidayBookingSystem.resource.ApiLayer;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.holidayBookingSystem.resource.DTO.*;



@Path("/bookings")
public class BookingAPI implements API<BookingWithRoomDTOWithPaymentDTOWithCustomerDTO, Integer>{
	
	private static final Logger LOGGER = Logger.getLogger(BookingAPI.class.getName());
	
	@GET
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getById(@PathParam("id") Integer id) {
		LOGGER.info("In Controller Get Booking with ID : " + id);
		BookingWithRoomDTOWithPaymentDTOWithCustomerDTO booking =  (BookingWithRoomDTOWithPaymentDTOWithCustomerDTO) bookingService.findById(id);
            if (booking == null) {
                return Response
                          .status(Response.Status.NOT_FOUND)
                          .entity("Booking not found")
                          .build();
            }
        
        return Response
                .status(Response.Status.OK)
                .entity(booking)
                .build();
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAll() {
		LOGGER.info("In GetALL Bookings Controller");
		return Response
				.status(Response.Status.OK)
				.entity(bookingService.findAll())
				.build();
	}

	@POST
	@Path("/rooms/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BookingWithRoomDTOWithPaymentDTOWithCustomerDTO DTO, @PathParam("id") Integer id) {
		LOGGER.info("In Controller Create Booking method");
        if (DTO == null | !isValid(DTO)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Booking data")
                    .build();
        }
        
        if (null == customerService.findById(DTO.getCustomer().getId()))
        	return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Customer not found")
                    .build();
        
        RoomDTOWithBookingsDTO room = (RoomDTOWithBookingsDTO) roomService.findById(id);
        if (null == room)
        	return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();

        
        DTO.setRoomWithHotel(room);
        return this.create(DTO);
//        if(!isCreated.booleanValue())
//	        return Response
//	                .status(Response.Status.NOT_ACCEPTABLE)
//	                .entity("Booking Already Exists for the Customer")
//	                .build();
//        
//        return Response
//                .status(Response.Status.CREATED)
//                .build();
    }
	
	@Override
	public Response create(BookingWithRoomDTOWithPaymentDTOWithCustomerDTO entity) {
		Boolean isCreated  = (Boolean) bookingService.create(entity);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

	private boolean isValid(BookingWithRoomDTOWithPaymentDTOWithCustomerDTO dTO) {
		if (dTO.getCustomer() != null && dTO.getStartDate() != null && dTO.getEndDate() != null)
			return true;
		return false;
	}
	
	

	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam("id") Integer id, BookingWithRoomDTOWithPaymentDTOWithCustomerDTO DTO) {
		
		LOGGER.info("In Controller update Booking id : " + id);
		if (DTO == null ) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Booking data")
                    .build();
        }

		BookingWithRoomDTOWithPaymentDTOWithCustomerDTO existingBooking = (BookingWithRoomDTOWithPaymentDTOWithCustomerDTO) bookingService.findById(id);
        if (existingBooking == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Booking not found")
                    .build();
        }
        
        Boolean isupdated  = (Boolean) bookingService.update(DTO, id);
        
        if (!isupdated) 
        	return Response
                .status(Response.Status.NOT_ACCEPTABLE)
                .build();
        
        return Response
                .status(Response.Status.OK)
                .build();
    }


	@DELETE
    @Path("/{id}")
    @Override
    public Response delete(@PathParam("id") Integer id) {
		LOGGER.info("In Controller delete booking id : "+ id);
		Boolean isDeleted = (Boolean) bookingService.delete(id);
        if (isDeleted.booleanValue()) {
            return Response
                    .status(Response.Status.OK)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Booking not found")
                    .build();
        }
	}

}

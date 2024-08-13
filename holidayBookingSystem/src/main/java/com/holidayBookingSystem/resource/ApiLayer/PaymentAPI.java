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

import com.holidayBookingSystem.resource.DTO.PaymentWithCustomerDTOWithBookingWithRoomDTO;
import com.holidayBookingSystem.resource.DTO.RoomDTOWithBookingsDTO;

@Path("/payments")
public class PaymentAPI implements API<PaymentWithCustomerDTOWithBookingWithRoomDTO, Integer>{

	private static final Logger LOGGER = Logger.getLogger(PaymentAPI.class.getName());
	
	@GET
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getById(@PathParam("id") Integer id) {
		LOGGER.info("In Controller Get Payment with ID : " + id);
		PaymentWithCustomerDTOWithBookingWithRoomDTO payment = (PaymentWithCustomerDTOWithBookingWithRoomDTO) paymentService.findById(id);
            if (payment == null) {
                return Response
                          .status(Response.Status.NOT_FOUND)
                          .entity("Payment not found")
                          .build();
            }
        
        return Response
                .status(Response.Status.OK)
                .entity(payment)
                .build();
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAll() {
		LOGGER.info("In GetALL Payments Controller");
		return Response.
				status(Response.Status.OK)
				.entity(paymentService.findAll())
				.build();
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response create(PaymentWithCustomerDTOWithBookingWithRoomDTO DTO) {
		LOGGER.info("In Controller Create Payment method");
        if (DTO == null | !isValid(DTO)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Payment data")
                    .build();
        }
        
        if (null == bookingService.findById(DTO.getBooking().getId()))
        	return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Booking not found")
                    .build();

     
        Boolean isCreated  = (Boolean) paymentService.create(DTO);
        if(!isCreated.booleanValue())
	        return Response
	                .status(Response.Status.NOT_ACCEPTABLE)
	                .entity("Payment Already Exists for the Booking")
	                .build();
        
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
	
	

	private boolean isValid(PaymentWithCustomerDTOWithBookingWithRoomDTO dTO) {
		if (dTO.getAmount() > 0 && dTO.getPaymentMethod() != null && dTO.getBooking() != null)
			return true;
		
		return false;
	}

	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
	public Response update(@PathParam("id") Integer id , PaymentWithCustomerDTOWithBookingWithRoomDTO DTO) {
		LOGGER.info("In Controller update Payment id : " + id);
			if (DTO == null) {
	            return Response
	                    .status(Response.Status.BAD_REQUEST)
	                    .entity("Invalid Payment data")
	                    .build();
	        }

			PaymentWithCustomerDTOWithBookingWithRoomDTO existingPayment = (PaymentWithCustomerDTOWithBookingWithRoomDTO) paymentService.findById(id);
	        if (existingPayment == null) {
	            return Response
	                    .status(Response.Status.NOT_FOUND)
	                    .entity("Payment not found")
	                    .build();
	        }
	        
	        Boolean isupdated  = (Boolean) paymentService.update(DTO, id);
	        
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
		LOGGER.info("In Controller delete Payment id : "+ id);
		Boolean isDeleted = (Boolean) paymentService.delete(id);
        if (isDeleted.booleanValue()) {
            return Response
                    .status(Response.Status.OK)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Payment not found")
                    .build();
        }
	}

}

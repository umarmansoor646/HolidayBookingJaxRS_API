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

import com.holidayBookingSystem.resource.DTO.CustomerWithBookingsDTO;

@Path("/customers")
public class CustomerAPI implements API<CustomerWithBookingsDTO, Integer>{

	private static final Logger LOGGER = Logger.getLogger(CustomerAPI.class.getName());
	
	@GET
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getById(@PathParam("id") Integer id) {
		LOGGER.info("In Controller Get Customer with ID : " + id);
		CustomerWithBookingsDTO customer =  (CustomerWithBookingsDTO) customerService.findById(id);
		
            if (customer == null) {
                return Response
                          .status(Response.Status.NOT_FOUND)
                          .entity("Customer not found")
                          .build();
            }
        
        return Response
                .status(Response.Status.OK)
                .entity(customer)
                .build();
    }

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAll() {
		LOGGER.info("In GetALL Customers Controller");
		return Response.
				status(Response.Status.OK)
				.entity(customerService.findAll())
				.build();
	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(CustomerWithBookingsDTO DTO) {
		LOGGER.info("In Controller Create Customer method");
        if (DTO == null | !isValid(DTO)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Customer data")
                    .build();
        }

     
        Boolean isCreated  = (Boolean) customerService.create(DTO);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }


	private boolean isValid(CustomerWithBookingsDTO dTO) {
		if (dTO.getName() != null && dTO.getEmail() != null)
			return true;
		return false;
	}


	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam("id") Integer id, CustomerWithBookingsDTO DTO) {
		LOGGER.info("In Controller update Customer id : " + id);
		if (DTO == null ) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Customer data")
                    .build();
        }

		CustomerWithBookingsDTO existingCustomer = (CustomerWithBookingsDTO) customerService.findById(id);
        if (existingCustomer == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Customer not found")
                    .build();
        }
        
        Boolean isupdated  = (Boolean) customerService.update(DTO, id);
        
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
		LOGGER.info("In Controller delete Customer id : "+ id);
		Boolean isDeleted = (Boolean) customerService.delete(id);
        if (isDeleted.booleanValue()) {
            return Response
                    .status(Response.Status.OK)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Customer not found")
                    .build();
        }
	}
}

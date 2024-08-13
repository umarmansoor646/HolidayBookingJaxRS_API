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

import com.holidayBookingSystem.resource.DTO.HotelWithRoomDTO;


@Path("/hotels")
public class HotelAPI implements API<HotelWithRoomDTO, Integer> {

	private static final Logger LOGGER = Logger.getLogger(HotelAPI.class.getName());
	
	@GET
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getById(@PathParam("id") Integer id) {
		LOGGER.info("In Controller Get Hotel with ID : " + id);
		 HotelWithRoomDTO hotel = (HotelWithRoomDTO) hotelService.findById(id);
            if (hotel == null) {
                return Response
                          .status(Response.Status.NOT_FOUND)
                          .entity("Hotel not found")
                          .build();
            }
        
        return Response
                .status(Response.Status.OK)
                .entity(hotel)
                .build();
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAll() {	
		LOGGER.info("In GetALL Hotels Controller");
		return Response
			      .status(Response.Status.OK)
			      .entity(hotelService.findAll())
			      .build();
	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(HotelWithRoomDTO DTO) {
		LOGGER.info("In Controller Create Hotel method");
        if (DTO == null | !isValid(DTO)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid hotel data")
                    .build();
        }

     
        Boolean isCreated  = (Boolean) hotelService.create(DTO);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam("id") Integer id, HotelWithRoomDTO DTO) {
		LOGGER.info("In Controller update Hotel id : " + id);
		if (DTO == null ) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid hotel data")
                    .build();
        }

		HotelWithRoomDTO existingHotel = (HotelWithRoomDTO) hotelService.findById(id);
        if (existingHotel == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Hotel not found")
                    .build();
        }
        
        Boolean isupdated  = (Boolean) hotelService.update(DTO, id);
        
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
		LOGGER.info("In Controller delete Hotel id : "+ id);
		Boolean isDeleted = (Boolean) hotelService.delete(id);
        if (isDeleted.booleanValue()) {
            return Response
                    .status(Response.Status.OK)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Hotel not found")
                    .build();
        }
	}
	
	private boolean isValid(HotelWithRoomDTO DTO) {
		if (DTO.getName() != null && DTO.getAddress() != null)
			return true;
		return false;
	}

	
}

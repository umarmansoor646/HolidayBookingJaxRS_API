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
import com.holidayBookingSystem.resource.DTO.RoomDTOWithBookingsDTO;

@Path("/rooms")
public class RoomAPI implements API<RoomDTOWithBookingsDTO, Integer>{

	private static final Logger LOGGER = Logger.getLogger(RoomAPI.class.getName());
	
	@GET
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getById(@PathParam("id") Integer id) {
		LOGGER.info("In Controller Get Room with ID : " + id);
		RoomDTOWithBookingsDTO room = (RoomDTOWithBookingsDTO) roomService.findById(id);
            if (room == null) {
                return Response
                          .status(Response.Status.NOT_FOUND)
                          .entity("Room not found")
                          .build();
            }
        
        return Response
                .status(Response.Status.OK)
                .entity(room)
                .build();
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAll() {
		LOGGER.info("In GetALL Rooms Controller");
		return Response
				.status(Response.Status.OK)
				.entity(roomService.findAll())
				.build();
	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(RoomDTOWithBookingsDTO DTO) {
		LOGGER.info("In Controller Create Room method");
        if (DTO == null | !isValid(DTO)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Room data")
                    .build();
        }
        
        if (null == hotelService.findById(DTO.getHotelDTO().getId()))
        	return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Hotel not found")
                    .build();

     
        Boolean isCreated  = (Boolean) roomService.create(DTO);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

	private boolean isValid(RoomDTOWithBookingsDTO DTO) {
		if (DTO.getRoomNumber() != null && DTO.getType() != null && DTO.getHotelDTO() != null)
			return true;
		return false;
	}

	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam("id") Integer id, RoomDTOWithBookingsDTO DTO) {
		LOGGER.info("In Controller update Room id : " + id);
		if (DTO == null ) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Room data")
                    .build();
        }

		RoomDTOWithBookingsDTO existingRoom = (RoomDTOWithBookingsDTO) roomService.findById(id);
        if (existingRoom== null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }
        
        Boolean isupdated  = (Boolean) roomService.update(DTO, id);
        
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
		LOGGER.info("In Controller delete Room id : "+ id);
		Boolean isDeleted = (Boolean) roomService.delete(id);
        if (isDeleted.booleanValue()) {
            return Response
                    .status(Response.Status.OK)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("ROOM not found")
                    .build();
        }
	}

}

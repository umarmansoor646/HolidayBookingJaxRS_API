package com.holidayBookingSystem.resource.ServiceLayer;

import java.util.ArrayList;
import java.util.List;

import com.holidayBookingSystem.resource.DAO.Hotel;
import com.holidayBookingSystem.resource.DTO.HotelWithRoomDTO;
import com.holidayBookingSystem.resource.util.DTO_Converter;

public class HotelService implements Service<HotelWithRoomDTO ,Integer, Boolean>{

	@Override
	public HotelWithRoomDTO findById(Integer id) {
		LOGGER.info("In Service Get Hotel with ID : " + id);
		for (Hotel hotel : dao.getAllHotels()) {
			if (hotel.getId() == id)
				return new HotelWithRoomDTO(hotel);
		}	
		
		return null;
	}

	@Override
	public List<HotelWithRoomDTO> findAll() {
		LOGGER.info("In GetALL Hotels Service");
		List<HotelWithRoomDTO> hotelsDTO = new ArrayList<>();	
		
		for (Hotel hotel : dao.getAllHotels()) {
			hotelsDTO.add(DTO_Converter.HotelToHotelWithRoomDtoConverter(hotel));
		}	
		return hotelsDTO;
	}

	@Override
	public Boolean delete(Integer id) {
		LOGGER.info("In Service delete Hotel id : " + id);
		return dao.deleteHotelByID(id);
	}

	@Override
	public Boolean create(HotelWithRoomDTO entity) {
		LOGGER.info("In Service Create Hotel method");
		return dao.createHotel(DTO_Converter.HotelWithRoomDTOTOHotelConverter(entity));
	}

	@Override
	public Boolean update(HotelWithRoomDTO entity, Integer id) {
		LOGGER.info("In Service update Hotel id : "+ id);
		return dao.updateHotel(DTO_Converter.HotelWithRoomDTOTOHotelConverter(entity), id);
	}

}

package com.holidayBookingSystem.resource.ServiceLayer;

import java.util.List;
import java.util.logging.Logger;

import com.holidayBookingSystem.resource.ApiLayer.PaymentAPI;
import com.holidayBookingSystem.resource.PersistanceLayer.DAO;

public interface Service<T , K, R> {
	
	public static final Logger LOGGER = Logger.getLogger(Service.class.getName());
	public static DAO dao = new DAO();
	
	T findById(K id);
    List<T> findAll();
    R create(T entity);
    R update(T entity, K id);
    R delete(K id);
}

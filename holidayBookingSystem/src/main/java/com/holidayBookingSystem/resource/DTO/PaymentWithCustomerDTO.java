package com.holidayBookingSystem.resource.DTO;

import com.holidayBookingSystem.resource.DAO.PaymentMethod;

public class PaymentWithCustomerDTO extends PaymentDTO{
	
	private CustomerDTO customer;

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public PaymentWithCustomerDTO() {}
	public PaymentWithCustomerDTO(PaymentMethod payment) {
		super(payment);
	}

	
	

}

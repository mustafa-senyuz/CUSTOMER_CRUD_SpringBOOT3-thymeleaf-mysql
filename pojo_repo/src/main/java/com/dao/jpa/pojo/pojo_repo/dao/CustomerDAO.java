package com.dao.jpa.pojo.pojo_repo.dao;

import java.util.List;

import com.dao.jpa.pojo.pojo_repo.entity.Customer;

public interface CustomerDAO {
																																						
	void addCustomer(Customer a_customer);
																																						
	List<Customer> getALLcustomer();
																																							
	boolean deleteCustomerbyID(int personalnumber);
																																								
	Customer findbyID(int personalnumber);

	void updateCustomerbyID(int personalnumber, Customer updatedCustomer) throws Exception;
}
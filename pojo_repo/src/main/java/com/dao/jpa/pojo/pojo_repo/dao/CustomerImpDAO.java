package com.dao.jpa.pojo.pojo_repo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.jpa.pojo.pojo_repo.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerImpDAO implements CustomerDAO {
													
	private EntityManager em;
	
	public CustomerImpDAO(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void addCustomer(Customer a_customer) {
																
		em.merge(a_customer);
		
	}

	
	@Override
	public List<Customer> getALLcustomer() {
	 
		String jpql = "SELECT c FROM Customer c";
	    return em.createQuery(jpql, Customer.class).getResultList();
	}

	@Override
	public boolean deleteCustomerbyID(int personalnumber) {
		
		Customer customerTOdelete = em.find(Customer.class, personalnumber);
		em.remove(customerTOdelete);
		return true;
		
	}

	@Override
	public Customer findbyID(int personalnumber) {
		
			return em.find(Customer.class, personalnumber);
	
	}

	@Override
	public void updateCustomerbyID(int personalnumber, Customer updatedCustomer) throws Exception {
		
		 Customer existingCustomer = em.find(Customer.class, personalnumber);
		 
		 System.out.println("bulunan müşteri" + existingCustomer);

		    // Müşteri bulunduysa, tempCustomer nesnesindeki bilgilerle güncelle
		    if (existingCustomer != null) {
		    	
		    	
		        existingCustomer.setName(updatedCustomer.getName());
		        existingCustomer.setEmail(updatedCustomer.getEmail());

		  System.out.println("udated ===> " + updatedCustomer);      
		        
		        em.merge(existingCustomer);
		        
				System.out.println("DB ===> " + existingCustomer); 
		        
		    } else {
		    		throw new Exception();
		    }
		
	}
}
package com.dao.jpa.pojo.pojo_repo.rest;																					
																						
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;						
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
																															
import com.dao.jpa.pojo.pojo_repo.dao.CustomerDAO;
import com.dao.jpa.pojo.pojo_repo.entity.Customer;
																						

@RestController
//@RequestMapping("/api")
public class RestControllerCUSTOMER {
	
	private List<Customer> customerlist = new ArrayList<>();
	
	private CustomerDAO customerDAO;																			

	
  	public RestControllerCUSTOMER(List<Customer> customerlist, CustomerDAO customerDAO) {			
		super();
		
		customerlist.add(new Customer());		
		customerlist.add(new Customer(596, "mustafa", "genel@gmail.com"));
		customerlist.add(new Customer(165151, "kadru", "activity@gmail.com"));	
		
		this.customerlist = customerlist;																	
		this.customerDAO = customerDAO;																				
	}

	@GetMapping("/api/alwayscustomers")
  	public List<Customer> ShowALLcustomers(){
  		return customerlist;
  	}
	
	
	@GetMapping("/api/customers")
  	public List<Customer> getALLcustomers(){
			
  		return customerDAO.getALLcustomer();
  	}

	@GetMapping("/find")
  	public Customer findbyID(@RequestParam("personalNUMBER") int personalNUMBER ){
		
		Customer wantedCustomer = customerDAO.findbyID(personalNUMBER);
		
		if (wantedCustomer !=null) {
	  		return customerDAO.findbyID(personalNUMBER);
		} else {
			
			Customer nullcustomer = new Customer("THERE IS NOT SUCH CUSTOMER",null);
			return nullcustomer;             
		}
  	}
																												
																					
																															
	@GetMapping("/findall")
  	public List<Customer> RetrieveALL(){
		
  		return customerDAO.getALLcustomer();
  	}
																													
																						
	
  	@PostMapping("/api/newcustomer")			//@Transactional olmadan yollamıyor
  	public void addNewCustomer() {
  																															
  		customerDAO.addCustomer(new Customer(000, "bosgiris","bos@gmail.com"));
  																															
  	}
  	
  	

  																							
  																						
  																													
  																													
  	@DeleteMapping("/delete")
  	public ResponseEntity<String> deleteCustomer(@RequestParam("personalnumber") int personalnumber) {
  		
  			Customer wantedCustomer = customerDAO.findbyID(personalnumber);
  			System.out.println(wantedCustomer);
  			
  			if(wantedCustomer!=null) {
  	  			customerDAO.deleteCustomerbyID(personalnumber);
  	  			
  	  			System.out.println(personalnumber + " personalnumber customer was deleted.");
  				String htmlContent = "<html><body><h3>THIS CUSTOMER " + personalnumber + " was deleted successfully...</h3></body></html>";
  	  	        
  				return ResponseEntity.ok(htmlContent);
  			}
  			else 
  			{        
  				System.out.println(personalnumber + "  personalnumber customer is not exist.");
  				String htmlContent = "<html><body><h3>THIS CUSTOMER (" + personalnumber  +  " ID PERSONEL) IS NOT EXIST, BECAUSE OF THAT COULDNT BE DELETED... </h3></body></html>";
  	        
  				return ResponseEntity.ok(htmlContent);
  	        }
  			

  	}
        

	   	
=======
package com.dao.jpa.pojo.pojo_repo.rest;																					
																						
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.jpa.pojo.pojo_repo.dao.CustomerDAO;
import com.dao.jpa.pojo.pojo_repo.dao.CustomerImpDAO;
import com.dao.jpa.pojo.pojo_repo.entity.Customer;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
																					
@RestController
//@RequestMapping("/api")
public class RestControllerCUSTOMER extends RepresentationModel<Customer>{
	
	private List<Customer> customerlist = new ArrayList<>();
	
	private   CustomerDAO customerDAO;			
	
	Link link = Link.of("http://localhost:8080/findall?", "all customers");
	
	
  	public RestControllerCUSTOMER(List<Customer> customerlist, CustomerDAO customerDAO) {			
		super();
		
		customerlist.add(new Customer());		
		customerlist.add(new Customer(596888, "mustafa", "genel@gmail.com"));
		customerlist.add(new Customer(165151, "kadru", "activity@gmail.com"));	
		
		this.customerlist = customerlist;																	
		this.customerDAO = customerDAO;																				
	}

	@GetMapping("/api/alwayscustomers")
  	public List<Customer> ShowALLcustomers(){
  		return customerlist;
  	}
	
	
	@GetMapping("/api/customers")
  	public List<Customer> getALLcustomers(){
			
  		return customerDAO.getALLcustomer();
  	}

	@GetMapping("/find")
  	public Customer findbyID(@RequestParam("personalNUMBER") int personalNUMBER ){
		
		Customer wantedCustomer = customerDAO.findbyID(personalNUMBER);

		
		if (wantedCustomer !=null) {
			wantedCustomer.add(link);
			
	  		return customerDAO.findbyID(personalNUMBER);
		} else {
			
			Customer nullcustomer = new Customer("THERE IS NOT SUCH CUSTOMER",null);
			return nullcustomer;             
		}
  	}
																												
																					
																															
    @GetMapping("/findall")
    public List<Customer> RetrieveALL() {

        List<Customer> customerlist = customerDAO.getALLcustomer();
       		
		  for (Customer customer : customerlist) { customer.addSelfLink(); }		/*hateoas*/	
		 
        return customerlist;
    }
																													
																						
	
  	@PostMapping("/api/newcustomer")			//@Transactional olmadan yollamıyor
  	public void addNewCustomer() {
  																															
  		customerDAO.addCustomer(new Customer(000, "bosgiris","bos@gmail.com"));
  																															
  	}
  																													
  																													
  	@DeleteMapping("/delete")
  	public ResponseEntity<String> deleteCustomer(@RequestParam("personalnumber") int personalnumber) {
  		
  			Customer wantedCustomer = customerDAO.findbyID(personalnumber);
  			System.out.println(wantedCustomer);
  			
  			if(wantedCustomer!=null) {
  	  			customerDAO.deleteCustomerbyID(personalnumber);
  	  			
  	  			System.out.println(personalnumber + " personalnumber customer was deleted.");
  				String htmlContent = "<html><body><h3>THIS CUSTOMER " + personalnumber + " was deleted successfully...</h3></body></html>";
  	  	        
  				return ResponseEntity.ok(htmlContent);
  			}
  			else 
  			{        
  				System.out.println(personalnumber + "  personalnumber customer is not exist.");
  				String htmlContent = "<html><body><h3>THIS CUSTOMER (" + personalnumber  +  " ID PERSONEL) IS NOT EXIST, BECAUSE OF THAT COULDNT BE DELETED... </h3></body></html>";
  	        
  				return ResponseEntity.ok(htmlContent);
  	        }
  	}
 

 }
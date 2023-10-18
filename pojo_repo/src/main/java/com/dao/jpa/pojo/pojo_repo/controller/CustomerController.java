package com.dao.jpa.pojo.pojo_repo.controller;
	
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.jpa.pojo.pojo_repo.dao.CustomerDAO;
import com.dao.jpa.pojo.pojo_repo.entity.Customer;
																														
																													
@Controller 
public class CustomerController {
	
//THYMELEAF KULLANMADIGIMDAN CALISMADI
	
	private CustomerDAO customerDAO;
	 
    public CustomerController(CustomerDAO customerDAO) {
				super();
				this.customerDAO = customerDAO;
    }
    
    @GetMapping("/")
    public String Redirect() {
    	return "customer-find-edit";
    }
    
    
    @GetMapping("/edit")
	public String FindDelete() {
		   return "customer-find-edit";
    }
   
    
	@GetMapping("/customerform")
    public String showCustomerForm() {
        return "customer-form"; 	// Bu, static klasöründe yer alan customerform.html dosyasını gösterecektir.
    }
																													
																													
																													
																													
																													
	

	@PostMapping("/submitForm")
	public String AddingTOdb(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {
		
		System.out.println("new record is "+ name + "  " + email );
		
		model.addAttribute("modelname", name);
		model.addAttribute("modelemail", email);
		
		customerDAO.addCustomer(new Customer(name,email));
		
		return "success-page";
	}
	
	
	
/*	
  	@PostMapping("/update")			 
  	public String updateCustomer(@RequestParam("personalTOupdate") int personalTOupdate, Model model) {
  																										
  			Customer oldpersonal = customerDAO.findbyID(personalTOupdate);
  			
  			if (oldpersonal==null) {
  				
  	           String response = "NO SUCH A CUSTOMER WITH THIS ID !!!";
  	            
  	           return response;
  	           
			} else {
				
	  			model.addAttribute("oldpersonalinfos", oldpersonal);
	  			
	  			return   "customer-update-form";
	
			}																														
  	}
*/	
	
	
	
	
	public ResponseEntity<String> updateCustomerSUB() {
		
        String response = "NO SUCH A CUSTOMER WITH THIS ID !!!";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        
	}
	
    @PostMapping("/update")
    public String updateCustomer(@RequestParam("personalTOupdate") int personalTOupdate, Model model) {
        
    	Customer oldpersonal = customerDAO.findbyID(personalTOupdate);
    	model.addAttribute("personalnumber", personalTOupdate);
    	
	        if (oldpersonal == null) {
	        		return "update-fail-page";
        	} else {
	 	            
	 	        	model.addAttribute("oldpersonalinfos", oldpersonal);
	 	            
	 	        	return "customer-update-form";
	        	
		        }
	} 
    
      	
	@PostMapping("/updateForm")
	public String updatePersonalINFOS(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("personalnumber") int personalnumber, Model model) throws Exception {
		
		System.out.println("updated record is "+ name + "  " + email );
		
		model.addAttribute("modelname", name);
		model.addAttribute("modelemail", email);
		
		Customer updatedCustomer = new Customer( name, email);
		
		customerDAO.updateCustomerbyID(personalnumber, updatedCustomer);
		
		return "success-page";
	}	
}	
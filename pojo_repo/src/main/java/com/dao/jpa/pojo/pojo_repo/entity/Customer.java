package com.dao.jpa.pojo.pojo_repo.entity;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.dao.jpa.pojo.pojo_repo.rest.RestControllerCUSTOMER;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends RepresentationModel<Customer> {
	
	@Column(name="personalnumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personalnumber;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;

	public Customer() {
		super();
		 
	}

	
	public Customer(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}


	public Customer(int personalnumber, String name, String email) {
		super();
		this.personalnumber = personalnumber;
		this.name = name;
		this.email = email;
	}

	public int getPersonalnumber() {
		return personalnumber;
	}

	public void setPersonalnumber(int personalnumber) {
		this.personalnumber = personalnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [personalnumber=" + personalnumber + ", name=" + name + ", email=" + email + "]";
	}

 
	/*hateoas*/																						//findbyID metodunu, @GetMapping anotasyonu ile işaretlenmiş olduğu için bulabilmesidir -- Bu anotasyon, findbyID metodunu /find URI’sine eşler.
    public void addSelfLink() {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestControllerCUSTOMER.class).findbyID(this.getPersonalnumber())).withSelfRel();
        this.add(selfLink);
    }
	 
}

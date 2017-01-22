package com.example.restejbjpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name = "person.getAll", query = "Select p from Person p"),
	@NamedQuery(name="person.deleteAll", query="Delete from Person")	
})
public class Person {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private int yob;
	
	private List<Car> cars = new ArrayList<>();
	
	public Person() {
	}

	public Person(String firstName, String lastName, int yob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.yob = yob;
	}	
	public Person(Long id, String firstName, int yob) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.yob = yob;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public void addCars(List<Car> cars) {
		
		this.setCars(cars);
		
		for (Car car: cars){
			car.getOwners().add(this);
		}
	}				
}

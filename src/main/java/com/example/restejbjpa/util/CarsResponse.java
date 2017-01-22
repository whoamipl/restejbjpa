package com.example.restejbjpa.util;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.restejbjpa.domain.Car;

@XmlRootElement
public class CarsResponse {
	
	private List<Car> car;

	public List<Car> getCar() {
		return car;
	}

	public void setCar(List<Car> car) {
		this.car = car;
	}

}

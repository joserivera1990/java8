package com.josecode.java8.optional;

import java.util.Optional;

public class Person {

	private Optional<Car> car;
	
	public void setCar(Optional<Car> car) {
		this.car = car;
	}

	public Optional<Car> getCar() {
		return car;
	}

}

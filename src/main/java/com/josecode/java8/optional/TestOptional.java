package com.josecode.java8.optional;

import java.util.Optional;
import java.util.Properties;

public class TestOptional {

	public static void main(String[] args) {
		Optional<Person> personNull = Optional.ofNullable(null);
		
		System.out.println(new TestOptional().getCarInsuranceName(personNull));
        
		//With Data		
		System.out.println(new TestOptional().getCarInsuranceName(buildPerson()));
		System.out.println(new TestOptional().getCarInsuranceNameAnotherSintaxis(buildPerson()));
		
	}
    
	public String getCarInsuranceName (Optional<Person> person) {
		return person.flatMap(Person::getCar)
				     .flatMap(Car::getInsurance)
				     .map(Insurance::getName)
				     .orElse("Unknown");
	}
	
	public String getCarInsuranceNameAnotherSintaxis (Optional<Person> person) {
		return person.flatMap(p -> p.getCar())
				     .flatMap(c -> c.getInsurance())
				     .map(i -> i.getName())
				     .orElse("Unknown");
	}
	
	private static Optional<Person> buildPerson() {
		Insurance insurance = new Insurance();
		insurance.setName("JOSE");
		Car car = new Car();
		car.setInsurance(Optional.of(insurance));
		Person person = new Person();
		person.setCar(Optional.of(car));
		return Optional.of(person);
	}
	
	private int readDuration(Properties props, String name) {
		return Optional.ofNullable(props.getProperty(name))
				       .flatMap(OptionalUtility::stringToInt)
                       .filter(i -> i > 0)
                       .orElse(0);
	}
	
	
}

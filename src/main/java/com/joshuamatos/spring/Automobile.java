package com.joshuamatos.spring;

import org.springframework.stereotype.Component;

@Component
public class Automobile {

	public double price;
	private final String makeAndModel;

	public Automobile() {
		price = 0;
		makeAndModel = "test";
	}
	//Constructor - takes Make and Make, price
	public Automobile(String makeAndModel, double price) {
		this.makeAndModel = makeAndModel;
		this.price = price;
	}

	//takes price and returns 5% of price
	public double salesTax() {
		return this.price * 0.05;
	}

	//Returns make and model, sales price, and tax of vehicle
	@Override
	public String toString() {
		return "Make and Model: " + this.makeAndModel + "\n" +
				"Sales Price: " + this.price + "\n" +
				"Tax: " + salesTax() + "\n";
	}

}
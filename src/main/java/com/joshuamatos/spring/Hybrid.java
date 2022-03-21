package com.joshuamatos.spring;

public class Hybrid extends Automobile {
	private final int mpg;

	//Constructor - takes Make and Make, price, and MPG - passes variables to parent class
	public Hybrid(String makeAndModel, double price, int mpg) {
		super(makeAndModel, price);
		this.mpg = mpg;
	}

	//Calculate sales tax - if MPG is less than 40, subtract 100 or subtract 100 plus 2 for every MPG above 40
	// @Override
	public double salesTax() {
		double discount = (this.mpg < 40) ? 100 : ((this.mpg - 40) * 2) - 100;
		//return super.salesTax() -
		return discount;
	}

	//Returns parent class string and adds MPG
	@Override
	public String toString() {
		return super.toString() + "MPG: " + this.mpg;
	}
}
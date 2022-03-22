package com.joshuamatos.spring;

public class Electric extends Automobile {
    private final int weight;

    //Constructor - takes Make and Make, price, and weight
    public Electric(String makeAndModel, double price, int weight) {
        super(makeAndModel, price);
        this.weight = weight;
    }

    //Calculates sales tax using parent class, adds discount. if weight is less than 3000, discount is 200 if not 150
    @Override
    public double salesTax() {
        double discount = (this.weight < 3000) ? 200 : 150;
        return super.salesTax() - discount;
    }

    //Returns parent class string and adds weight
    @Override
    public String toString() {
        return super.toString() + "Weight: " + this.weight;
    }
}
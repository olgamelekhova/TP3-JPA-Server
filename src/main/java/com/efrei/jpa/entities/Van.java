package com.efrei.jpa.entities;


import javax.persistence.Entity;

@Entity
public class Van extends Vehicle {
    private double maxWeight;

    protected Van() {
    }

    public Van(String plateNumber, double maxWeight) {
        super(plateNumber);

        this.maxWeight = maxWeight;
    }

    public Van(String plateNumber, Rent rent, double maxWeight) {
        super(plateNumber, rent);

        this.maxWeight = maxWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb
                .append('(')
                .append(super.toString())
                .append("), maxWeight: ")
                .append(maxWeight)
                .append(")").toString();
    }
}

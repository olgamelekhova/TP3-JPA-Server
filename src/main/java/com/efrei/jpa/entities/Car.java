package com.efrei.jpa.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@JsonTypeName("car")
@Entity
public class Car extends Vehicle {
    private int numberOfSeats;

    protected Car() {
    }

    public Car(String plateNumber, int numberOfSeats) {
        super(plateNumber);

        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb
                .append('(')
                .append(super.toString())
                .append("), numberOfSeats: ")
                .append(numberOfSeats)
                .append(")").toString();
    }
}

package com.efrei.jpa.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String plateNumber;

    @ManyToOne
    private Rent rent;

    Vehicle() {
    }

    Vehicle(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    Vehicle(String plateNumber, Rent rent) {
        this.plateNumber = plateNumber;
        this.rent = rent;
    }

    public Long getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb
                .append("(plateNumber: ")
                .append(plateNumber)
                .append(", rent: ")
                .append(rent)
                .append(")").toString();
    }
}
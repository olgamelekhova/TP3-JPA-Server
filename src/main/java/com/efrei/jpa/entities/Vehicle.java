package com.efrei.jpa.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String plateNumber;

    @OneToMany(targetEntity = Rent.class,
            mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rent> rents = new ArrayList<>();

    Vehicle() {
    }

    Vehicle(String plateNumber) {
        this.plateNumber = plateNumber;
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

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb
                .append("(plateNumber: ")
                .append(plateNumber)
                .append(", rent: ")
                .append(")").toString();
    }
}
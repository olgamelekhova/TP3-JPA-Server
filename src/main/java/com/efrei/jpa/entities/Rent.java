package com.efrei.jpa.entities;

import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginRent;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date endRent;

    @ManyToOne
    @NotNull
    private Person person;

    @OneToMany(targetEntity = Vehicle.class,
            mappedBy = "rent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehicle> vehicles = new ArrayList<>();

    protected Rent() {
    }

    public Rent(Date beginRent, Date endRent, Person person) {
        this.beginRent = beginRent;
        this.endRent = endRent;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public Date getBeginRent() {
        return beginRent;
    }

    public void setBeginRent(Date beginRent) {
        this.beginRent = beginRent;
    }

    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb
                .append("(beginRent: ")
                .append(beginRent)
                .append(", endRent: ")
                .append(endRent)
                .append(", person: ")
                .append(person).toString();
    }
}
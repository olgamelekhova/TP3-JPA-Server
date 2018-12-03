package com.efrei.jpa.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

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

    private boolean active;

    @ManyToOne
    @NonNull
    private Vehicle vehicle;

    @ManyToOne
    @NonNull
    private Person person;

    protected Rent() {
    }

    public Rent(Date beginRent, Date endRent, Person person, Vehicle vehicle) {
        this.beginRent = beginRent;
        this.endRent = endRent;
        this.person = person;
        this.vehicle = vehicle;
        this.active = true;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb
                .append("(beginRent: ")
                .append(beginRent)
                .append(", endRent: ")
                .append(endRent)
                .append(", active: ")
                .append(active)
                .append(", person: ")
                .append(person).toString();
    }
}
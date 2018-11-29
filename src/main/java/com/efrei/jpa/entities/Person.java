package com.efrei.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @JsonIgnore
    @OneToMany(targetEntity = Rent.class, mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rent> rents = new ArrayList<>();

    protected Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .append("(name: ")
                .append(name)
                .append(")").toString();
    }
}

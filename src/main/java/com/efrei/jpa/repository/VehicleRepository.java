package com.efrei.jpa.repository;

import com.efrei.jpa.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle findByPlateNumber(String plateNumber);
}

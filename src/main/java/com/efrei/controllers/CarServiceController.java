package com.efrei.controllers;

import com.efrei.jpa.entities.Person;
import com.efrei.jpa.entities.Rent;
import com.efrei.jpa.entities.Vehicle;
import com.efrei.jpa.repository.PersonRepository;
import com.efrei.jpa.repository.RentRepository;
import com.efrei.jpa.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CarServiceController {
    private final PersonRepository personRepository;
    private final RentRepository rentRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public CarServiceController(PersonRepository personRepository, RentRepository rentRepository,
                                VehicleRepository vehicleRepository) {
        this.personRepository = personRepository;
        this.rentRepository = rentRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/rents", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Rent> listOfRents() {
        return rentRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/rents/{plateNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<Rent>> findRentsByPlateNumber(@PathVariable("plateNumber") String plateNumber) {
        return Optional.ofNullable(vehicleRepository.findByPlateNumber(plateNumber))
                .map(vehicle -> ResponseEntity.ok(vehicle.getRents()))
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/vehicles", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Vehicle> listOfCars() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicles::add);

        return vehicles;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/vehicles/{plateNumber}", method = RequestMethod.GET)
    public ResponseEntity<Vehicle> findVehicleByPlateNumber(@PathVariable("plateNumber") String plateNumber) {
        return Optional.ofNullable(vehicleRepository.findByPlateNumber(plateNumber))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/vehicles/{plateNumber}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Rent> rentVehicle(@PathVariable(name = "plateNumber") String plateNumber,
                                            @RequestParam(name = "person") String personName,
                                            @RequestParam(value = "beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date beginDate,
                                            @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate,
                                            @RequestParam(name = "rent") boolean toRent) {
        Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber);

        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }

        Rent rent = vehicle.getRents()
                .stream()
                .filter(r -> r.getBeginRent().compareTo(beginDate) == 0
                        && r.getEndRent().compareTo(endDate) == 0
                        && r.getPerson().getName().equalsIgnoreCase(personName))
                .findAny()
                .orElse(null);

        if (rent == null) {
            if (toRent &&
                    vehicle.getRents()
                            .stream()
                            .noneMatch(r -> r.getBeginRent().getTime() <= endDate.getTime()
                                    && beginDate.getTime() <= r.getEndRent().getTime())) {

                Person person = new Person(personName);
                Rent newRent = new Rent(beginDate, endDate, person, vehicle);

                personRepository.save(person);
                rentRepository.save(newRent);

                return ResponseEntity.ok(newRent);
            }

            return ResponseEntity.notFound().build();
        }

        if ((toRent && !rent.isActive()) || (!toRent && rent.isActive())) {
            rent.setActive(toRent);
            rentRepository.save(rent);
            return ResponseEntity.ok(rent);
        }

        return ResponseEntity.badRequest().build();
    }
}

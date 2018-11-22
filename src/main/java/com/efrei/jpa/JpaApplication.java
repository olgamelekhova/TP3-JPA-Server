package com.efrei.jpa;

import com.efrei.jpa.entities.Car;
import com.efrei.jpa.entities.Person;
import com.efrei.jpa.entities.Rent;
import com.efrei.jpa.entities.Van;
import com.efrei.jpa.repository.PersonRepository;
import com.efrei.jpa.repository.RentRepository;
import com.efrei.jpa.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class JpaApplication {
    private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PersonRepository personRepository, RentRepository rentRepository,
                                  VehicleRepository vehicleRepository) {
        return (args) -> {
            Person person = new Person("person");
            Rent rent1 = new Rent(dateFormat.parse("2018-10-09"), dateFormat.parse("2018-10-10"), person);
            Rent rent2 = new Rent(dateFormat.parse("2018-11-10"), dateFormat.parse("2018-11-15"), person);

            Van van = new Van("54321B", rent1, 300.);
            Car car = new Car("12345A", rent1, 2);

            personRepository.save(person);
            log.info("\n==========================");
            log.info("Person saved: " + person);
            log.info("==========================\n");

            rentRepository.save(rent1);
            rentRepository.save(rent2);
            log.info("==========================");
            log.info("Rent saved: " + rent1 + ", " + rent2);
            log.info("==========================\n");

            vehicleRepository.save(car);
            vehicleRepository.save(van);
            log.info("==========================");
            log.info("Vehicle saved: " + car + ", " + van);
            log.info("==========================\n");


            log.info("==========================");
            log.info("Persons found:");
            personRepository.findByName("person").forEach(p -> log.info(p.toString()));
            log.info("==========================\n");


            log.info("==========================");
            log.info("Rent found: " + rentRepository.findById(rent1.getId()).get());
            log.info("==========================\n");

            log.info("==========================");
            log.info("Car found: " + vehicleRepository.findByPlateNumber("12345A"));
            log.info("==========================\n");
        };
    }
}

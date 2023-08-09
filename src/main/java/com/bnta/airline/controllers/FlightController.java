package com.bnta.airline.controllers;

import com.bnta.airline.models.Flight;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.services.FlightService;
import com.bnta.airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    FlightRepository flightRepository;

    //gets all flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flights= flightService.findAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // get a certain flight by using flight id
    @GetMapping(value="/{id}")
    public ResponseEntity<Flight> getFLight(@PathVariable Long id){
        Optional<Flight> flight = flightService.findFlight(id);
        if(flight.isPresent()) {
            return new ResponseEntity<>(flight.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    // add new flight
    public ResponseEntity<List<Flight>> addFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return new ResponseEntity<>(flightRepository.findAll(),HttpStatus.OK);
    }




}

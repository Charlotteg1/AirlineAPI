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

    //gets all flights //ADD OPTIONAL: DESTINATION FILTER
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam Optional<String> destination){
        List<Flight> flights;
        if(destination.isPresent()){
            flights = flightService.findByDestination(destination.get());
            if(flights.size()==0){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
        flights= flightService.findAllFlights();
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
    @PostMapping
    public ResponseEntity<List<Flight>> addFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return new ResponseEntity<>(flightRepository.findAll(),HttpStatus.CREATED);
    }

    //update a flight
    @PutMapping(value="/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight, @PathVariable Long id){
        Flight updatedFlight= flightService.updateFlight(flight,id);
        return new ResponseEntity<>(updatedFlight,HttpStatus.OK);
    }

    //cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteFlight(@PathVariable Long id){
        flightService.removeFlightsFromPassengers(id);
        flightService.deleteFlight(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }



}

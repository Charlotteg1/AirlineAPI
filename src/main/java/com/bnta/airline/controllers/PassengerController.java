package com.bnta.airline.controllers;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.models.PassengerDTO;
import com.bnta.airline.repositories.PassengerRepository;
import com.bnta.airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    PassengerRepository passengerRepository;

    //gets all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        List<Passenger> passengers= passengerService.findAllPassengers();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    // gets a certain passenger
    @GetMapping(value="/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable Long id){
        Optional<Passenger> passenger = passengerService.findPassenger(id);
        if(passenger.isPresent()) {
            return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    // add new passenger
    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@RequestBody PassengerDTO passengerDTO){
        Passenger savedPassenger = passengerService.savePassenger(passengerDTO);
        return new ResponseEntity<>(savedPassenger,HttpStatus.CREATED);
    }

    // update passenger or book passenger on to flight
    @PutMapping(value="/{id}")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody PassengerDTO passengerDTO, @PathVariable Long id){
        Passenger updatedPassenger= passengerService.updatePassenger(passengerDTO,id);
        return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
    }

    // delete a passenger
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Long> deletePassenger(@PathVariable Long id){
        passengerService.deletePassenger(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

}

package com.bnta.airline.services;

import com.bnta.airline.models.Flight;
import com.bnta.airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    // below need to be in controller also
    // add flight
    // update flight
    // get flight by id
    // get all flights
    // delete (cancel flight)

    @Autowired
    FlightRepository flightRepository;

    public void saveFlight(Flight flight){
        flightRepository.save(flight);
    }
    // lists all flights
    public List<Flight> findAllFlights(){ return  flightRepository.findAll();}

    //gives specific flight
    public Optional<Flight> findFlight(long id){ return  flightRepository.findById(id);}
}

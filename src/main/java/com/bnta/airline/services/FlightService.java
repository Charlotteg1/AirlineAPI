package com.bnta.airline.services;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    // below need to be in controller also
    // add flight*
    // update flight*
    // get flight by id*
    // get all flights*
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

    //update flight
    public Flight updateFlight(Flight flight , Long id){
        Flight flightToUpdate= flightRepository.findById(id).get();
        flightToUpdate.setDestination(flight.getDestination());
        flightToUpdate.setCapacity(flight.getCapacity());
        flightToUpdate.setDepartureDate(flight.getDepartureDate());
        flightToUpdate.setDepartureTime(flight.getDepartureTime());
        flightRepository.save(flightToUpdate);
        return flightToUpdate;
    }

    //delete a flight from passengers
    public void removeFlightsFromPassengers(Long id){
        Flight foundPassenger = flightRepository.getById(id);
        for (Passenger passenger : foundPassenger.getPassengers()){
            passenger.removeFlight(foundPassenger);
        }
    }

    //delete flight
    public void deleteFlight(Long id){
        flightRepository.deleteById(id);
    }

    public List<Flight> findByDestination(String destination){
    return flightRepository.findByDestination(destination);
    }


}

package com.bnta.airline.services;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.models.PassengerDTO;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    // add passenger
    // display all passengers
    // display specific passengers
    //book passenger

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public Passenger savePassenger(PassengerDTO passengerDTO){
        Passenger passenger= new Passenger(passengerDTO.getName(), passengerDTO.getEmailAddress());
        for(Long flightId: passengerDTO.getFlightIds()){
            Flight flight= flightRepository.findById(flightId).get();
            passenger.addFlight(flight);
        }
        return passengerRepository.save(passenger);
    }

    // get all passengers
    public List<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }

    // find certain passenger
    public Optional<Passenger> findPassenger(Long id){
        return passengerRepository.findById(id);
    }
}

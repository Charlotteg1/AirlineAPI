package com.bnta.airline.services;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.models.PassengerDTO;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    // add passenger*
    // display all passengers*
    // display specific passengers*
    //book passenger / update passenger on to flight or can change details (email)

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public Passenger savePassenger(PassengerDTO passengerDTO){
        Passenger passenger= new Passenger(passengerDTO.getName(), passengerDTO.getEmailAddress());
        for(Long flightId : passengerDTO.getFlightIds()){
            Flight flight= flightRepository.findById(flightId).get();
            // call fight to find capacity and number of people on flight
            if(flight.getCapacity()>flight.getPassengers().size()){
                passenger.addFlight(flight);
            }
        }
        return passengerRepository.save(passenger);
    }

    // get all passengers
    public List<Passenger> findAllPassengers(){
        return passengerRepository.findAll();
    }

    // find certain passenger
    public Optional<Passenger> findPassenger(Long id){
        return passengerRepository.findById(id);
    }

    // update Passenger, can also book on to flight
    public Passenger updatePassenger(PassengerDTO passengerDTO, Long id){
        Passenger passengerToUpdate= passengerRepository.findById(id).get();
        passengerToUpdate.setName(passengerDTO.getName());
        passengerToUpdate.setEmailAddress(passengerDTO.getEmailAddress());
        passengerToUpdate.setFlights(new ArrayList<>());
        for (Long flightId : passengerDTO.getFlightIds()){
            Flight flight= flightRepository.findById(flightId).get();
            if(flight.getCapacity()>flight.getPassengers().size()){
                passengerToUpdate.addFlight(flight);
            }
        }
        passengerRepository.save(passengerToUpdate);
        return passengerToUpdate;
    }

    //delete passenger
    public void deletePassenger(Long id){
        passengerRepository.deleteById(id);
    }
}

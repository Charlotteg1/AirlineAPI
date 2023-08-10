package com.bnta.airline.components;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception{

        // Flight to Paris
        Flight parisFlight= new Flight("Paris",180, LocalDate.of(2023, 10, 5), LocalTime.of(12,35));
        flightRepository.save(parisFlight);

        Passenger passenger1= new Passenger("Mary Cooper","mcooper@gmail.com");
        passenger1.addFlight(parisFlight);
        passengerRepository.save(passenger1);

        Passenger passenger2= new Passenger("Lexi Brown","lexib@hotmail.com");
        passenger2.addFlight(parisFlight);
        passengerRepository.save(passenger2);

        Passenger passenger3= new Passenger("Alexander Ward ","aw1994@gmail.com");
        passenger3.addFlight(parisFlight);
        passengerRepository.save(passenger3);

        //Flight to Madrid
        Flight madridFlight= new Flight("Madrid",120, LocalDate.of(2023, 11, 23), LocalTime.of(17,55));
        flightRepository.save(madridFlight);

        Passenger passenger4= new Passenger("Ashley Jones","ashleyj@gmail.com");
        passenger4.addFlight(madridFlight);
        passengerRepository.save(passenger4);

        Passenger passenger5= new Passenger("Miguel Lee","miguel1978@aol.com");
        passenger5.addFlight(madridFlight);
        passengerRepository.save(passenger5);

        //Flight to Rome
        Flight romeFlight= new Flight("Rome",68, LocalDate.of(2023, 8, 17), LocalTime.of(07,25));
        flightRepository.save(romeFlight);

        Passenger passenger6= new Passenger("Rosanne Jones","rosanneJ34@gmail.com");
        passenger6.addFlight(romeFlight);
        passengerRepository.save(passenger6);

        Passenger passenger7= new Passenger("Andy Andrews","andyandrews1@aol.com");
        passenger7.addFlight(madridFlight);
        passengerRepository.save(passenger7);

        Passenger passenger8= new Passenger("Frank Smith","franksmith123@hotmail.com");
        passenger8.addFlight(romeFlight);
        passengerRepository.save(passenger8);

        Passenger passenger9= new Passenger("Maddie Squire","madssquire@gmail.com");
        passenger9.addFlight(romeFlight);
        passengerRepository.save(passenger9);

        //passengers taking multiple flights
        Passenger passenger10= new Passenger("Alan Driscoll","adriscoll76@gmail.com");
        passenger10.addFlight(madridFlight);
        passenger10.addFlight(parisFlight);
        passengerRepository.save(passenger10);

        Passenger passenger11= new Passenger("Caroline Yan","miguel1978@aol.com");
        passenger11.addFlight(romeFlight);
        passenger11.addFlight(parisFlight);
        passengerRepository.save(passenger11);

    }

}

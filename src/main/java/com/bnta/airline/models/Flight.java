package com.bnta.airline.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String destination;

    @Column
    private int capacity;

    @Column(name="departure_date")
    private LocalDate departureDate;

    @Column(name="departure_time")
    private LocalTime departureTime;

    @JsonIgnoreProperties({"flights"})
    @ManyToMany(mappedBy = "flights")
    private List<Passenger> passengers;


    public Flight(String destination,int capacity,LocalDate departureDate,LocalTime departureTime) {
        this.id = id;
        this.destination = destination;
        this.capacity = capacity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
    }

    public Flight(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}

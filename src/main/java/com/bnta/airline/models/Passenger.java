package com.bnta.airline.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column(name="email_address")
    private String emailAddress;

    @ManyToMany
    @JsonIgnoreProperties({"passengers"})
    @JoinTable(
            name="passengers_flights",
            joinColumns = @JoinColumn(name="passengers_id"),
            inverseJoinColumns = @JoinColumn(name="flights_id")
    )
    private List<Flight> flights;

    public Passenger(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.flights = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight){this.flights.add(flight);}
}

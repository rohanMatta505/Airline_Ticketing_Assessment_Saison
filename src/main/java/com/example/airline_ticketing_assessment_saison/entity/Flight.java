package com.example.airline_ticketing_assessment_saison.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    private String flightNumber;
    private String originCode;
    private String destinationCode;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int capacity;
    private int availableSeats;

    @OneToMany(mappedBy = "flight")
    private List<Schedule> schedules;
    // getters and setters
}

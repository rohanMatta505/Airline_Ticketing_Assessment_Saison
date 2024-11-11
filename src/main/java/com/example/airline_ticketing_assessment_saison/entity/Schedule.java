package com.example.airline_ticketing_assessment_saison.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private List<String> daysOfWeekAvailable;  // E.g., ["M", "W", "F"] for Mondays, Wednesdays, Fridays
    private LocalTime departureTime;
    private Duration flightDuration;
    private LocalDate startDate;  // To start calculating future available dates
    private LocalDate endDate;    // 365 days from startDate

    @ManyToOne
    private LocationCode origin;

    @ManyToOne
    private LocationCode destination;

}


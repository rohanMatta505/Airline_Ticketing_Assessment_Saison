package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findByFlightNumber(String flightNumber);
}
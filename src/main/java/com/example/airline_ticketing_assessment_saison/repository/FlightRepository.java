package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Custom query method to find a flight by flight number
    Flight findByFlightNumber(String flightNumber);
    Flight findById(Long id);
}
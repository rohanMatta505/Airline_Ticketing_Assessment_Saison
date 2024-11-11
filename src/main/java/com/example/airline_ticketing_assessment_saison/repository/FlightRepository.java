package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Custom query method to find a flight by flight number
    Optional<Flight> findByFlightNumber(String flightNumber);
    Optional<Flight> findById(Long id);
}
package com.example.airline_ticketing_assessment_saison.service;

import com.example.airline_ticketing_assessment_saison.entity.Flight;
import com.example.airline_ticketing_assessment_saison.repository.BookingRepository;
import com.example.airline_ticketing_assessment_saison.repository.FlightRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatManager {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private BookingRepository bookingRepository;

    // Check if a seat is available
    public boolean isSeatAvailable(Long flightId, int seatNumber) {
        Optional<Flight> flight = flightRepository.findById(flightId);

        // Check if the flight exists and the seat number is valid
        if (flight.isPresent() && seatNumber > 0 && seatNumber <= flight.get().getCapacity()) {
            // Seat is available if it is within the capacity and within available seats
            return flight.get().getAvailableSeats() > 0;
        }
        return false;
    }

    // Book a seat
    public boolean bookSeat(Long flightId, int seatNumber) {
        Optional<Flight> flight = flightRepository.findById(flightId);

        if (flight.getAvailableSeats() > 0 && isSeatAvailable(flightId, seatNumber)) {
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            flightRepository.save(flight);
            return true;
        }
        return false;
    }

    // Release a seat (for cancellations)
    public void releaseSeat(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);

        if (flight.isPresent()) {
            Flight currentFlight = flight.get();

            // Increase the available seat count (release the seat)
            currentFlight.setAvailableSeats(currentFlight.getAvailableSeats() + 1);

            // Save the updated flight data
            flightRepository.save(currentFlight);
        }
    }
}
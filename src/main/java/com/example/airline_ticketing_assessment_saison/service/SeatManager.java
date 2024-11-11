package com.example.airline_ticketing_assessment_saison.service;

import com.example.airline_ticketing_assessment_saison.entity.Flight;
import com.example.airline_ticketing_assessment_saison.repository.BookingRepository;
import com.example.airline_ticketing_assessment_saison.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatManager {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public boolean isSeatAvailable(Long flightId, int seatNumber) {
        //checking if the seatnumber is available in the provided flightId
        Optional<Flight> flight = flightRepository.findById(flightId);

        if (flight.isPresent() && seatNumber > 0 && seatNumber <= flight.get().getCapacity()) {
            return flight.get().getAvailableSeats() > 0;
        }
        return false;
    }

    public boolean bookSeat(Long flightId, int seatNumber) {
        //booking 1 seat and reducing available seats by 1
        Flight flight = flightRepository.findById(flightId);

        if (flight.getAvailableSeats() > 0 && isSeatAvailable(flightId, seatNumber)) {
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            flightRepository.save(flight);
            return true;
        }
        return false;
    }

    public void releaseSeat(Long flightId) {
        //releasing 1 seat and increasing seats available by 1
        Flight flight = flightRepository.findById(flightId);
        if (flight.isPresent()) {
            Flight currentFlight = flight.get();
            currentFlight.setAvailableSeats(currentFlight.getAvailableSeats() + 1);
            flightRepository.save(currentFlight);
        }
    }
}
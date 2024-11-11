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

    public boolean isSeatAvailable(Long flightId, int seatNumber) {
        Flight flight = flightRepository.findById(flightId);

        if (flight.isPresent() && seatNumber > 0 && seatNumber <= flight.get().getCapacity()) {
            return flight.get().getAvailableSeats() > 0;
        }
        return false;
    }

    public boolean bookSeat(Long flightId, int seatNumber) {
        Flight flight = flightRepository.findById(flightId);

        if (flight.getAvailableSeats() > 0 && isSeatAvailable(flightId, seatNumber)) {
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            flightRepository.save(flight);
            return true;
        }
        return false;
    }

    public void releaseSeat(Long flightId) {
        Flight flight = flightRepository.findById(flightId);

        if (flight.isPresent()) {
            Flight currentFlight = flight.get();

            currentFlight.setAvailableSeats(currentFlight.getAvailableSeats() + 1);

            flightRepository.save(currentFlight);
        }
    }
}
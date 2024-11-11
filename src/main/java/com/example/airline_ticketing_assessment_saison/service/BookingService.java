package com.example.airline_ticketing_assessment_saison.service;

import com.example.airline_ticketing_assessment_saison.entity.Booking;
import com.example.airline_ticketing_assessment_saison.repository.BookingRepository;
import com.example.airline_ticketing_assessment_saison.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;

    public Booking makeBooking(Long flightId, Long customerId, int seatSelected, String paymentMode) {
        // Check seat availability, create booking if available, update available seats
    }

    public void cancelBooking(Long bookingId) {
        // Retrieve booking, mark as canceled, update available seats
    }
}

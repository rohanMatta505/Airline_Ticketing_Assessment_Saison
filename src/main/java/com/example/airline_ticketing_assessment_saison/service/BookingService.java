package com.example.airline_ticketing_assessment_saison.service;

import com.example.airline_ticketing_assessment_saison.entity.Booking;
import com.example.airline_ticketing_assessment_saison.entity.Customer;
import com.example.airline_ticketing_assessment_saison.entity.Flight;
import com.example.airline_ticketing_assessment_saison.repository.BookingRepository;
import com.example.airline_ticketing_assessment_saison.repository.CustomerRepository;
import com.example.airline_ticketing_assessment_saison.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SeatManager seatManager;

    public Booking makeBooking(Long flightId, Long customerId, int seatSelected, String paymentMode) {
        Flight flight = flightRepository.findById(flightId);
        Customer customer = customerRepository.findById(customerId);
        if (seatManager.bookSeat(flightId, seatSelected)) {
            Booking booking = new Booking();
            booking.setFlight(flight);
            booking.setCustomer(customer);
            booking.setSeatSelected(seatSelected);
            booking.setPaymentMode(paymentMode);
            booking.setPaymentStatus("Pending");
            booking.setBookingDate(LocalDateTime.now());
            bookingRepository.save(booking);
        }
        else {
            throw new IllegalArgumentException("Seat not available");
        }
        return booking;
    }
    public void cancelBooking(Long bookingId) {
        Booking bookingOPt = bookingRepository.findById(bookingId);

        if (!bookingOpt.isPresent()) {
            throw new IllegalArgumentException("Booking not found");
        }

        Booking booking = bookingOPt.get();
        Long flightId = booking.getFlight().getId();
        seatManager.releaseSeat(flightId);

        bookingRepository.delete(booking);
    }
}

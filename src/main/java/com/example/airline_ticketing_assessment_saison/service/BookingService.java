package com.example.airline_ticketing_assessment_saison.service;

import com.example.airline_ticketing_assessment_saison.entity.Booking;
import com.example.airline_ticketing_assessment_saison.entity.Customer;
import com.example.airline_ticketing_assessment_saison.entity.Flight;
import com.example.airline_ticketing_assessment_saison.repository.BookingRepository;
import com.example.airline_ticketing_assessment_saison.repository.CustomerRepository;
import com.example.airline_ticketing_assessment_saison.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Optional<Flight> flight = flightRepository.findById(flightId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        Booking booking = new Booking();
        if (seatManager.bookSeat(flightId, seatSelected)) {
            booking.setFlight(flight.get());
            booking.setCustomer(customer.get());
            booking.setSeatSelected(seatSelected);
            booking.setPaymentMode(paymentMode);
            booking.setPaymentStatus("Pending");
            booking.setBookingDate(LocalDate.from(LocalDateTime.now()));
            bookingRepository.save(booking);
        }
        else {
            throw new IllegalArgumentException("Seat not available");
        }
        return booking;
    }
    public void cancelBooking(Long bookingId) {
        Optional<Booking> bookingById = bookingRepository.findById(bookingId);
        Booking booking = bookingById.get();
        Long flightId = booking.getFlight().getId();
        seatManager.releaseSeat(flightId);

        bookingRepository.delete(booking);
    }
}

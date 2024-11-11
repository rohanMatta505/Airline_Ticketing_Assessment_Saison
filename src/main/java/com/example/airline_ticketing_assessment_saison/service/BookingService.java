package com.example.airline_ticketing_assessment_saison.service;

import com.example.airline_ticketing_assessment_saison.entity.Booking;
import com.example.airline_ticketing_assessment_saison.entity.Customer;
import com.example.airline_ticketing_assessment_saison.entity.Flight;
import com.example.airline_ticketing_assessment_saison.repository.BookingRepository;
import com.example.airline_ticketing_assessment_saison.repository.CustomerRepository;
import com.example.airline_ticketing_assessment_saison.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Booking makeBooking(Long flightId, Long customerId, int seatSelected, String paymentMode) {
        Flight flight = flightRepository.findById(flightId);
        if (seatSelected > flight.getAvailableSeats()) {
            throw new IllegalArgumentException("Seat selection exceeds available seats.");
        }
        Customer customer = customerRepository.findById(customerId);

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setCustomer(customer);
        booking.setSeatSelected(seatSelected);
        booking.setPaymentMode(paymentMode);
        booking.setPaymentStatus("Pending");
        booking.setBookingDate(LocalDateTime.now());

        bookingRepository.save(booking);
        flight.setAvailableSeats(flight.getAvailableSeats() - seatSelected);
        flightRepository.save(flight);
        return booking;
    }
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        Flight flight = booking.getFlight();
        booking.setPaymentStatus("Canceled");
        bookingRepository.save(booking);
        flight.setAvailableSeats(flight.getAvailableSeats() + booking.getSeatSelected());
        flightRepository.save(flight);
    }
}

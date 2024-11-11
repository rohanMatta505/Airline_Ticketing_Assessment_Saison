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
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        // Check if the seat is available
        if (seatSelected > flight.getAvailableSeats()) {
            throw new IllegalArgumentException("Seat selection exceeds available seats.");
        }

        // Retrieve customer and check if it exists
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Create a booking
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setCustomer(customer);
        booking.setSeatSelected(seatSelected);
        booking.setPaymentMode(paymentMode);
        booking.setPaymentStatus("Pending");  // Assume 'Pending' status for new bookings
        booking.setBookingDate(LocalDateTime.now());

        // Save the booking
        bookingRepository.save(booking);

        // Update available seats for the flight
        flight.setAvailableSeats(flight.getAvailableSeats() - seatSelected);
        flightRepository.save(flight);

        return booking;
    }

    public void cancelBooking(Long bookingId) {
        // Retrieve booking, mark as canceled, update available seats
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Retrieve the associated flight
        Flight flight = booking.getFlight();

        // Mark the booking as canceled
        booking.setPaymentStatus("Canceled");
        bookingRepository.save(booking);

        // Update the available seats for the flight
        flight.setAvailableSeats(flight.getAvailableSeats() + booking.getSeatSelected());
        flightRepository.save(flight);
    }
}

package com.example.airline_ticketing_assessment_saison.controller;

import com.example.airline_ticketing_assessment_saison.entity.BookingRequest;
import com.example.airline_ticketing_assessment_saison.entity.Booking;
import com.example.airline_ticketing_assessment_saison.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public Booking makeBooking(@RequestBody BookingRequest request) {
        //initiating booking by passing details like customerId, flightId in request body
        System.out.println("Making a booking");
        return bookingService.makeBooking(request.getFlightId(), request.getCustomerId(), request.getSeatSelected(), request.getPaymentMode());
    }

    @DeleteMapping("/cancellation/{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId) {
        //cancel Booking with bookingId
        System.out.println("Cancelling booking");
        bookingService.cancelBooking(bookingId);
    }
}

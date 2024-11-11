package com.example.airline_ticketing_assessment_saison.entity;

import lombok.Data;

@Data
public class BookingRequest {
    private Long flightId;       // ID of the flight for the booking
    private Long customerId;     // ID of the customer making the booking
    private int seatSelected;    // Number of seats selected for the booking
    private String paymentMode;
}

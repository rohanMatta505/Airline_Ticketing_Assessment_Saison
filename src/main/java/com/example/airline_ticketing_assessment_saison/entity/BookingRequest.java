package com.example.airline_ticketing_assessment_saison.entity;

import lombok.Data;

@Data
public class BookingRequest {
    private Long flightId;
    private Long customerId;
    private int seatSelected;
    private String paymentMode;
}

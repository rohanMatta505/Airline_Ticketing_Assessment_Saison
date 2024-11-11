package com.example.airline_ticketing_assessment_saison.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int seatSelected;
    private LocalDate bookingDate;
    private String paymentStatus;
    private String paymentMode;

    private String originCode;
    private String destinationCode;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
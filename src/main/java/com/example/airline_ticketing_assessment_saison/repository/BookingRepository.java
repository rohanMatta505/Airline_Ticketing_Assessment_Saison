package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Booking;
import com.example.airline_ticketing_assessment_saison.entity.Flight;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByFlightAndSeatSelected(Long flightId, int seatSelected);
    Booking findById(Long bookingId);

}

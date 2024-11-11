package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findById(Long bookingId);
}

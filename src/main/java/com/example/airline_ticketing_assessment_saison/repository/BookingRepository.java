package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}

package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByOriginCodeAndDestinationCode(String originCode, String destinationCode);
}
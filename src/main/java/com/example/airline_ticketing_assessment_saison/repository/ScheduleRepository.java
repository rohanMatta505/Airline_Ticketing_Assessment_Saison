package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Schedule;
import java.util.*;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Custom query method to find schedules by origin and destination codes
    List<Schedule> findByOriginCodeAndDestinationCode(String originCode, String destinationCode);
}
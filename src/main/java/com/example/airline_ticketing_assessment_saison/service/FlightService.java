package com.example.airline_ticketing_assessment_saison.service;

import com.example.airline_ticketing_assessment_saison.entity.DayOfWeek;
import com.example.airline_ticketing_assessment_saison.entity.Flight;
import com.example.airline_ticketing_assessment_saison.entity.Schedule;
import com.example.airline_ticketing_assessment_saison.repository.FlightRepository;
import com.example.airline_ticketing_assessment_saison.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Flight> searchFlights(String originCode, String destinationCode, LocalDate date) {
        List<Flight> matchingFlights = new ArrayList<>();

        List<Schedule> schedules = scheduleRepository.findByOriginCodeAndDestinationCode(originCode, destinationCode);

        for (Schedule schedule : schedules) {
            List<LocalDate> availableDates = getAvailableDates(schedule);
            if (availableDates.contains(date)) {
                //flights which match the searched date
                Flight flight = flightRepository.findByFlightNumber(schedule.getFlightNumber());
                matchingFlights.add(flight);
            }
        }
        //returning list of flights which match the search result filter
        return matchingFlights;
    }


    public List<LocalDate> getAvailableDates(Schedule schedule) {
        List<LocalDate> availableDates = new ArrayList<>();
        LocalDateTime startDate = schedule.getStartDate().atStartOfDay();
        LocalDate endDate = schedule.getEndDate();

        List<DayOfWeek.Day> daysOfWeek = schedule.getDaysOfWeekAvailable().stream()
                .map(this::mapDayStringToDayOfWeek)
                .collect(Collectors.toList()).reversed();

        for (LocalDate date = LocalDate.from(startDate); date.isBefore(endDate); date = date.plusDays(1)) {
            // Checking if the current date's day of the week is in the list of available days
            if (daysOfWeek.contains(date.getDayOfWeek())) {
                availableDates.add(date);
            }
        }
        return availableDates;
    }
    private DayOfWeek.Day mapDayStringToDayOfWeek(String day) {
        switch (day) {

            case "MO": return DayOfWeek.Day.MONDAY;
            case "TU": return DayOfWeek.Day.TUESDAY;
            case "WE": return DayOfWeek.Day.WEDNESDAY;
            case "TH": return DayOfWeek.Day.THURSDAY;
            case "FR": return DayOfWeek.Day.FRIDAY;
            case "SA": return DayOfWeek.Day.SATURDAY;
            case "SU": return DayOfWeek.Day.SUNDAY;
        }
        return null;
    }
}
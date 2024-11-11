package com.example.airline_ticketing_assessment_saison.controller;

import com.example.airline_ticketing_assessment_saison.entity.Flight;
import com.example.airline_ticketing_assessment_saison.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam String origin, @RequestParam String destination, @RequestParam LocalDate date) {
        //search flights with origin code,destination code and date
        System.out.println("Searching flights from " + origin + " to " + destination);
        return flightService.searchFlights(origin, destination, date);
    }
}
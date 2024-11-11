package com.example.airline_ticketing_assessment_saison.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LocationCode {
    @Id
    private String code;
    private String city;
    // getters and setters
}

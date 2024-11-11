package com.example.airline_ticketing_assessment_saison.repository;

import com.example.airline_ticketing_assessment_saison.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
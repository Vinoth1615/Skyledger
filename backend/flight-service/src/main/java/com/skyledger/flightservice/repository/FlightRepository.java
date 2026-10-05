package com.skyledger.flightservice.repository;

import com.skyledger.flightservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findByDepartureAirportAndArrivalAirport(
            String departureAirport,
            String arrivalAirport
    );

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
            String departureAirport,
            String arrivalAirport,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Flight> findByAirlineName(String airlineName);

}
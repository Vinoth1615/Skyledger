package com.skyledger.flightservice.service;

import com.skyledger.flightservice.dto.FlightRequest;
import com.skyledger.flightservice.dto.FlightResponse;
import com.skyledger.flightservice.exception.DuplicateFlightException;
import com.skyledger.flightservice.exception.FlightNotFoundException;
import com.skyledger.flightservice.model.Flight;
import com.skyledger.flightservice.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightResponse createFlight(FlightRequest request) {

        if (flightRepository.findByFlightNumber(request.getFlightNumber()).isPresent()) {
            throw new DuplicateFlightException("Flight number already exists: " + request.getFlightNumber());
        }

        Flight flight = Flight.builder()
                .flightNumber(request.getFlightNumber())
                .airlineName(request.getAirlineName())
                .departureAirport(request.getDepartureAirport())
                .arrivalAirport(request.getArrivalAirport())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getTotalSeats())
                .aircraftType(request.getAircraftType())
                .build();

        Flight saved = flightRepository.save(flight);
        return FlightResponse.fromEntity(saved);
    }

    public FlightResponse getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
        return FlightResponse.fromEntity(flight);
    }

    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(FlightResponse::fromEntity)
                .toList();
    }

    public List<FlightResponse> searchFlights(String from, String to, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return flightRepository
                .findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(from, to, startOfDay, endOfDay)
                .stream()
                .map(FlightResponse::fromEntity)
                .toList();
    }

    public FlightResponse updateFlight(Long id, FlightRequest request) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        flight.setAirlineName(request.getAirlineName());
        flight.setDepartureAirport(request.getDepartureAirport());
        flight.setArrivalAirport(request.getArrivalAirport());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setTotalSeats(request.getTotalSeats());
        flight.setAircraftType(request.getAircraftType());

        Flight updated = flightRepository.save(flight);
        return FlightResponse.fromEntity(updated);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }

}
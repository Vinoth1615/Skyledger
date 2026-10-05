package com.skyledger.flightservice.exception;

public class DuplicateFlightException extends RuntimeException {
    public DuplicateFlightException(String message) {
        super(message);
    }
}
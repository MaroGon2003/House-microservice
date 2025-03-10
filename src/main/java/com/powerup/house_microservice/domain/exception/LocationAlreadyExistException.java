package com.powerup.house_microservice.domain.exception;

public class LocationAlreadyExistException extends RuntimeException {

    public LocationAlreadyExistException(String message) {
        super(message);
    }
}

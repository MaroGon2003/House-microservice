package com.powerup.house_microservice.domain.exception;

public class StateNotFoundException extends RuntimeException {

    public StateNotFoundException(String message) {
        super(message);
    }

}

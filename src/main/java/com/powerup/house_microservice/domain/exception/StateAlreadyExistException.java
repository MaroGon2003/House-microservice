package com.powerup.house_microservice.domain.exception;

public class StateAlreadyExistException extends RuntimeException {

    public StateAlreadyExistException(String message) {
        super(message);
    }
}

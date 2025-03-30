package com.powerup.house_microservice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class StateAlreadyExistException extends RuntimeException {

    public StateAlreadyExistException(String message, String name) {
        super(String.format(message, name));
    }

}

package com.powerup.house_microservice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RealEstateCategoryAlreadyExistException extends RuntimeException {

    public RealEstateCategoryAlreadyExistException(String message, String name) {
        super(String.format(message, name));
    }

}

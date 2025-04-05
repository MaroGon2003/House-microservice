package com.powerup.house_microservice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RealEstateCategoryNotFoundException extends RuntimeException{
    public RealEstateCategoryNotFoundException(String message, Long realEstateCategoryId) {
        super(String.format(message, realEstateCategoryId));
    }
}

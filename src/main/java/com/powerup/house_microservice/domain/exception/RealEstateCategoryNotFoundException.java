package com.powerup.house_microservice.domain.exception;

public class RealEstateCategoryNotFoundException extends RuntimeException {

    public RealEstateCategoryNotFoundException(String message) {
        super(message);
    }
}

package com.powerup.house_microservice.domain.exception;

public class RealEstateCategoryAlreadyExistException extends RuntimeException {
    public RealEstateCategoryAlreadyExistException(String message) {
        super(message);
    }
}

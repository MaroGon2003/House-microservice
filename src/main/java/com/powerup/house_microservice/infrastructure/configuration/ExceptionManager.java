package com.powerup.house_microservice.infrastructure.configuration;

import com.powerup.house_microservice.domain.exception.LocationAlreadyExistException;
import com.powerup.house_microservice.domain.exception.RealEstateCategoryAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionManager {

    private static final String ERROR = "error";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> illegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(RealEstateCategoryAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> realEstateCategoryAlreadyExistException(RealEstateCategoryAlreadyExistException e){
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(LocationAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> locationAlreadyExistException(LocationAlreadyExistException e){
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

}

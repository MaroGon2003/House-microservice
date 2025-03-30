package com.powerup.house_microservice.infrastructure.configuration;

import com.powerup.house_microservice.domain.exception.*;
import com.powerup.house_microservice.infrastructure.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> illegalArgumentException(IllegalArgumentException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StateAlreadyExistException.class)
    public ResponseEntity<ApiResponse> stateAlreadyExistException(StateAlreadyExistException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RealEstateCategoryAlreadyExistException.class)
    public ResponseEntity<ApiResponse> realEstateCategoryAlreadyExistException(RealEstateCategoryAlreadyExistException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ApiResponse> cityNotFoundException(CityNotFoundException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StateNotFoundException.class)
    public ResponseEntity<ApiResponse> stateNotFoundException(StateNotFoundException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {

        Map<String, String> mapErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            mapErrors.put(field, message);
        });

        ApiResponse apiResponse = new ApiResponse(mapErrors.toString(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}

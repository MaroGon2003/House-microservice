package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.LocationRequestDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final ILocationHandler locationHandler;

    public LocationController(ILocationHandler locationHandler) {
        this.locationHandler = locationHandler;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLocation(@RequestBody @Valid LocationRequestDto locationDto) {

        locationHandler.saveLocation(locationDto);

        return ResponseEntity.ok("Location Saved");

    }

}

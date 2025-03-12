package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final ILocationHandler locationHandler;

    public LocationController(ILocationHandler locationHandler) {
        this.locationHandler = locationHandler;
    }

    @PostMapping("/saveState")
    public ResponseEntity<String> saveState(@RequestBody @Valid StateRequestDto stateRequestDto) {

        locationHandler.saveState(stateRequestDto);

        return ResponseEntity.ok("State Saved");

    }

    @PostMapping("/saveCity/{stateId}")
    public ResponseEntity<String> saveCity(@RequestBody @Valid CityRequestDto city, @PathVariable Long stateId) {

        locationHandler.saveCity(city, stateId);

        return ResponseEntity.ok("City Saved");

    }

}

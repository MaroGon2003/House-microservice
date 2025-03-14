package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import com.powerup.house_microservice.application.utils.PagedResult;
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

    @PostMapping("/save-state")

    public ResponseEntity<String> saveState(@RequestBody @Valid StateRequestDto stateRequestDto) {

        locationHandler.saveState(stateRequestDto);

        return ResponseEntity.ok("State Saved");

    }

    @PostMapping("/save-city/{stateId}")

    public ResponseEntity<String> saveCity(@RequestBody @Valid CityRequestDto city, @PathVariable Long stateId) {

        locationHandler.saveCity(city, stateId);

        return ResponseEntity.ok("City Saved");

    }

    @GetMapping("/get-all-locations-by-city-or-state-name")
    public PagedResult<LocationResponseDto> getAllLocationsByCityName(@RequestParam String name, @RequestParam String searchBy, @RequestParam int page, @RequestParam int size, @RequestParam boolean ascending) {

        String sortDirection = ascending ? "ASC" : "DESC";

        return locationHandler.getAllLocationsByCityName(name, searchBy, page, size, sortDirection);


    }

}

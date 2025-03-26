package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.LocationRequestDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import com.powerup.house_microservice.application.utils.PagedResult;
import com.powerup.house_microservice.infrastructure.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@Tag(name = "Locations", description = "Endpoints for managing locations")
public class LocationController {

    private final ILocationHandler locationHandler;

    public LocationController(ILocationHandler locationHandler) {
        this.locationHandler = locationHandler;
    }


    @Operation(summary = "Create location", description = "Create a new location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Location created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    @PostMapping
    public ResponseEntity<Void> createLocation(@RequestBody @Valid LocationRequestDto locationRequestDto) {
        locationHandler.create(locationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Get locations", description = "Retrieve a paginated list of locations filtered by state and city name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of locations retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    @GetMapping
    public ResponseEntity<PagedResult<LocationResponseDto>> getLocations(@RequestParam(required = false) String stateName,
                                                                         @RequestParam(required = false) String cityName,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size,
                                                                         @RequestParam(defaultValue = "true") boolean ascending) {

        String sortDirection = ascending ? Constants.ACS : Constants.DESC;

        PagedResult<LocationResponseDto> result = locationHandler.getLocations(stateName, cityName, page, size, sortDirection);

        return ResponseEntity.ok(result);
    }

}

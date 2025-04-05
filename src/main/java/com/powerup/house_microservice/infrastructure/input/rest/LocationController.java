package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.LocationRequestDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import com.powerup.house_microservice.application.utils.PagedResult;
import com.powerup.house_microservice.infrastructure.utils.InfrastructureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(InfrastructureConstants.LOCATION_CONTROLLER_REQUEST_MAPPING)
@Tag(name = InfrastructureConstants.LOCATION_CONTROLLER_TAG_NAME, description = InfrastructureConstants.LOCATION_CONTROLLER_TAG_DESCRIPTION)
public class LocationController {

    private final ILocationHandler locationHandler;

    public LocationController(ILocationHandler locationHandler) {
        this.locationHandler = locationHandler;
    }

    @Operation(summary = InfrastructureConstants.LOCATION_CONTROLLER_OPERATION_CREATE_SUMMARY, description = InfrastructureConstants.LOCATION_CONTROLLER_OPERATION_CREATE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_201, description = InfrastructureConstants.LOCATION_CONTROLLER_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.LOCATION_CONTROLLER_RESPONSE_400_DESCRIPTION)
    })
    @PostMapping
    public ResponseEntity<Void> createLocation(@RequestBody @Valid LocationRequestDto locationRequestDto) {
        locationHandler.create(locationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = InfrastructureConstants.LOCATION_CONTROLLER_OPERATION_GET_SUMMARY, description = InfrastructureConstants.LOCATION_CONTROLLER_OPERATION_GET_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_200, description = InfrastructureConstants.LOCATION_CONTROLLER_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.LOCATION_CONTROLLER_RESPONSE_400_DESCRIPTION)
    })
    @GetMapping
    public ResponseEntity<PagedResult<LocationResponseDto>> getLocations(@RequestParam(required = false) String stateName,
                                                                         @RequestParam(required = false) String cityName,
                                                                         @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_PAGE) int page,
                                                                         @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_SIZE) int size,
                                                                         @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_ASCENDING) boolean ascending) {
        return ResponseEntity.ok(locationHandler.getLocations(stateName, cityName, page, size, ascending));
    }
}

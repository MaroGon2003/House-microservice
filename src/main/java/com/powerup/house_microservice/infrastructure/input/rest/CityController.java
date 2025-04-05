package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.handler.ICityHandler;
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
@RequestMapping(InfrastructureConstants.CITY_CONTROLLER_REQUEST_MAPPING)
@Tag(name = InfrastructureConstants.CITY_CONTROLLER_TAG_NAME, description = InfrastructureConstants.CITY_CONTROLLER_TAG_DESCRIPTION)
public class CityController {

    private final ICityHandler cityHandler;

    public CityController(ICityHandler cityHandler) {
        this.cityHandler = cityHandler;
    }

    @Operation(
            summary = InfrastructureConstants.CITY_CONTROLLER_OPERATION_SUMMARY,
            description = InfrastructureConstants.CITY_CONTROLLER_OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_201, description = InfrastructureConstants.CITY_CONTROLLER_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.CITY_CONTROLLER_RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.CITY_CONTROLLER_RESPONSE_500_DESCRIPTION)
    })
    @PostMapping(InfrastructureConstants.CITY_CONTROLLER_POST_MAPPING)
    public ResponseEntity<Void> createCity(@RequestBody @Valid CityRequestDto city, @PathVariable Long stateId) {
        cityHandler.create(city, stateId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

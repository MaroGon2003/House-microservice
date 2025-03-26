package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.handler.ICityHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
@Tag(name = "Cities", description = "Endpoints for managing cities")
public class CityController {

    private final ICityHandler cityHandler;

    public CityController(ICityHandler cityHandler) {
        this.cityHandler = cityHandler;
    }

    @Operation(
            summary = "Create a new city",
            description = "Creates a new city and returns a 201 Created response."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "City created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body" , content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/states/{stateId}/cities")
    public ResponseEntity<Void> createCity(@RequestBody @Valid CityRequestDto city, @PathVariable Long stateId) {

        cityHandler.create(city, stateId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

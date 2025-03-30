package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.handler.IStateHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
@Tag(name = "States", description = "Endpoints for managing states")
public class StateController {

    private final IStateHandler stateHandler;

    public StateController(IStateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Operation(
            summary = "Create a new state",
            description = "Creates a new state and returns a 201 Created response."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "State created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "409" , description = "State already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Void> createState(@RequestBody @Valid StateRequestDto stateRequestDto) {

        stateHandler.create(stateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.handler.IStateHandler;
import com.powerup.house_microservice.infrastructure.utils.InfrastructureConstants;
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
@RequestMapping(InfrastructureConstants.STATE_CONTROLLER_REQUEST_MAPPING)
@Tag(name = InfrastructureConstants.STATE_CONTROLLER_TAG_NAME, description = InfrastructureConstants.STATE_CONTROLLER_TAG_DESCRIPTION)
public class StateController {

    private final IStateHandler stateHandler;

    public StateController(IStateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Operation(
            summary = InfrastructureConstants.STATE_CONTROLLER_OPERATION_SUMMARY,
            description = InfrastructureConstants.STATE_CONTROLLER_OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_201, description = InfrastructureConstants.STATE_CONTROLLER_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.STATE_CONTROLLER_RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.STATE_CONTROLLER_RESPONSE_500_DESCRIPTION)
    })
    @PostMapping
    public ResponseEntity<Void> createState(@RequestBody @Valid StateRequestDto stateRequestDto) {
        stateHandler.create(stateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
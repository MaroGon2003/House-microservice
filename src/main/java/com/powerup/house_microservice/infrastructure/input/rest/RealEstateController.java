package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.RealEstateRequestDto;
import com.powerup.house_microservice.application.handler.IRealEstateHandler;
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
@RequestMapping(InfrastructureConstants.REAL_ESTATE_CONTROLLER_REQUEST_MAPPING)
@Tag(name = InfrastructureConstants.REAL_ESTATE_CONTROLLER_TAG_NAME, description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_TAG_DESCRIPTION)
public class RealEstateController {

    private final IRealEstateHandler realEstateHandler;

    public RealEstateController(IRealEstateHandler realEstateHandler) {
        this.realEstateHandler = realEstateHandler;
    }

    @Operation(
            summary = InfrastructureConstants.REAL_ESTATE_CONTROLLER_OPERATION_SUMMARY,
            description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_201, description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_RESPONSE_500_DESCRIPTION)
    })
    @PostMapping
    public ResponseEntity<String> createRealEstate(@RequestBody @Valid RealEstateRequestDto realEstateRequestDto) {
        realEstateHandler.createRealEstate(realEstateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

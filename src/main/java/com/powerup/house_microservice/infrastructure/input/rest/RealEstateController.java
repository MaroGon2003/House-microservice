package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.RealEstateRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateResponseDto;
import com.powerup.house_microservice.application.handler.IRealEstateHandler;
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

import java.math.BigDecimal;

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

    @Operation(
            summary = InfrastructureConstants.REAL_ESTATE_CONTROLLER_OPERATION_GET_SUMMARY,
            description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_OPERATION_GET_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_200, description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.REAL_ESTATE_CONTROLLER_RESPONSE_500_DESCRIPTION)
    })
    @GetMapping
    public ResponseEntity<PagedResult<RealEstateResponseDto>> getRealEstate(
                                                @RequestParam(required = false) String stateName,
                                                @RequestParam(required = false) String cityName,
                                                @RequestParam(required = false) Long categoryId,
                                                @RequestParam(required = false) Integer rooms,
                                                @RequestParam(required = false) Integer bathrooms,
                                                @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_MIN_PRICE) BigDecimal minPrice,
                                                @RequestParam(required = false) BigDecimal maxPrice,
                                                @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_PAGE) int page,
                                                @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_SIZE) int size,
                                                @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_ASCENDING) boolean ascending) {

        return ResponseEntity.ok(realEstateHandler.getRealEstates(stateName, cityName, categoryId, rooms, bathrooms, minPrice, maxPrice, page, size, ascending));
    }
}

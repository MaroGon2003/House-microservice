package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;
import com.powerup.house_microservice.application.handler.IRealEstateCategoryHandler;
import com.powerup.house_microservice.application.utils.PagedResult;
import com.powerup.house_microservice.infrastructure.utils.InfrastructureConstants;
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
@RequestMapping(InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_REQUEST_MAPPING)
@Tag(name = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_TAG_NAME, description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_TAG_DESCRIPTION)
public class RealEstateCategoryController {

    private final IRealEstateCategoryHandler realEstateCategoryHandler;

    public RealEstateCategoryController(IRealEstateCategoryHandler realEstateCategoryHandler) {
        this.realEstateCategoryHandler = realEstateCategoryHandler;
    }

    @Operation(
            summary = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_CREATE_SUMMARY,
            description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_CREATE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_201, description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_500_DESCRIPTION)
    })
    @PostMapping
    public ResponseEntity<String> createRealEstateCategory(@RequestBody @Valid RealEstateCategoryRequestDto realEstateCategoryRequestDto) {
        realEstateCategoryHandler.saveRealEstateCategory(realEstateCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_GET_SUMMARY,
            description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_GET_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_200, description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_400_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_500_DESCRIPTION)
    })
    @GetMapping
    public ResponseEntity<PagedResult<RealEstateCategoryResponseDto>> getAllRealEstateCategories(
            @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_ASCENDING) boolean ascending) {
        PagedResult<RealEstateCategoryResponseDto> result = realEstateCategoryHandler.getAllRealEstateCategories(page, size, ascending);
        return ResponseEntity.ok(result);
    }
}

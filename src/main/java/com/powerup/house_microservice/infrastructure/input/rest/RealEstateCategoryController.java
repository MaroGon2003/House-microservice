package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;
import com.powerup.house_microservice.application.handler.IRealEstateCategoryHandler;
import com.powerup.house_microservice.application.utils.PagedResult;
import com.powerup.house_microservice.infrastructure.utils.Constants;
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
@RequestMapping("/real-estate-categories")
@Tag(name = "Real Estate Categories", description = "Endpoints for managing real estate categories")
public class RealEstateCategoryController {

    private final IRealEstateCategoryHandler realEstateCategoryHandler;

    public RealEstateCategoryController(IRealEstateCategoryHandler realEstateCategoryHandler) {
        this.realEstateCategoryHandler = realEstateCategoryHandler;
    }

    @Operation(
            summary = "Create a new real estate category",
            description = "Creates a new real estate category and returns a 201 Created response."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<String> createRealEstateCategory(@RequestBody @Valid RealEstateCategoryRequestDto realEstateCategoryRequestDto) {

        realEstateCategoryHandler.saveRealEstateCategory(realEstateCategoryRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");
    }

    @Operation(
            summary = "Get all real estate categories",
            description = "Retrieves a paginated list of all real estate categories, with sorting options."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of categories retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<PagedResult<RealEstateCategoryResponseDto>> getAllRealEstateCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean ascending) {

        String sortDirection = ascending ? Constants.ACS : Constants.DESC;

        PagedResult<RealEstateCategoryResponseDto> result = realEstateCategoryHandler.getAllRealEstateCategories(page, size, sortDirection);

        return ResponseEntity.ok(result);
    }

}

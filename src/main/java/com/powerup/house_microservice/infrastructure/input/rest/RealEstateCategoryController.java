package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;
import com.powerup.house_microservice.application.handler.IRealEstateCategoryHandler;
import com.powerup.house_microservice.application.handler.impl.RealEstateCategoryHandler;
import com.powerup.house_microservice.application.utils.PagedResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/real-estate-category")
public class RealEstateCategoryController {

    private final IRealEstateCategoryHandler realEstateCategoryHandler;

    public RealEstateCategoryController(RealEstateCategoryHandler realEstateCategoryHandler) {
        this.realEstateCategoryHandler = realEstateCategoryHandler;
    }

    //Admin user can save a real estate category
    @PostMapping("/save")
    public ResponseEntity<String> saveRealEstateCategory(@RequestBody @Valid RealEstateCategoryRequestDto realEstateCategoryRequestDto) {

        realEstateCategoryHandler.saveRealEstateCategory(realEstateCategoryRequestDto);

        return ResponseEntity.ok("Real Estate Category Saved");

    }

    //All users can get all real estate categories
    @GetMapping("/all")
    public ResponseEntity<PagedResult<RealEstateCategoryResponseDto>> getAllRealEstateCategories(int page, int size, String sortBy, boolean ascending) {

        String sortDirection = ascending ? "ASC" : "DESC";

        PagedResult<RealEstateCategoryResponseDto> result = realEstateCategoryHandler.getAllRealEstateCategories(page, size, sortBy, sortDirection);

        return ResponseEntity.ok(result);

    }

}

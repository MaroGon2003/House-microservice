package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;
import com.powerup.house_microservice.application.handler.impl.RealEstateCategoryHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/real-estate-category")
public class RealEstateCategoryController {

    private final RealEstateCategoryHandler realEstateCategoryHandler;

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
    public ResponseEntity<List<RealEstateCategoryResponseDto>> getAllRealEstateCategories(@RequestParam int pageNumber, @RequestParam int pageSize) {

        return ResponseEntity.ok(realEstateCategoryHandler.getAllRealEstateCategories(pageNumber, pageSize));

    }

}

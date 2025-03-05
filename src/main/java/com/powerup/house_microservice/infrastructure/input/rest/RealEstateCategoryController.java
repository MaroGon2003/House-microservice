package com.powerup.house_microservice.infrastructure.input.rest;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryDto;
import com.powerup.house_microservice.application.handler.impl.RealEstateCategoryHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EstateCategory")
public class RealEstateCategoryController {

    private final RealEstateCategoryHandler realEstateCategoryHandler;

    public RealEstateCategoryController(RealEstateCategoryHandler realEstateCategoryHandler) {
        this.realEstateCategoryHandler = realEstateCategoryHandler;
    }

    //Admin user can save a real estate category
    @PostMapping("/save")
    public ResponseEntity<String> saveRealEstateCategory(@RequestBody @Valid RealEstateCategoryDto realEstateCategoryDto) {

        realEstateCategoryHandler.saveRealEstateCategory(realEstateCategoryDto);

        return ResponseEntity.ok("Real Estate Category Saved");

    }

}

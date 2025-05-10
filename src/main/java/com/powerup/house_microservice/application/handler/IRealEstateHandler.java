package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.RealEstateRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateResponseDto;
import com.powerup.house_microservice.application.utils.PagedResult;

import java.math.BigDecimal;

public interface IRealEstateHandler {

    void createRealEstate(RealEstateRequestDto realEstateRequestDto);

    PagedResult<RealEstateResponseDto> getRealEstates(String stateName, String cityName, Long categoryId, Integer rooms, Integer bathrooms, BigDecimal minPrice, BigDecimal maxPrice, int page, int size, boolean ascending);

    boolean existsById(Long id);
}

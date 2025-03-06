package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryRequestDto;

import java.util.List;

public interface IRealEstateCategoryHandler {
    void saveRealEstateCategory(RealEstateCategoryRequestDto realEstateCategoryRequestDto);

    List<com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto> getAllRealEstateCategories(int pageNumber, int pageSize);

}

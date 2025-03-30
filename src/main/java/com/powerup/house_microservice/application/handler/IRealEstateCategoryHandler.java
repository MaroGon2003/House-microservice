package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;
import com.powerup.house_microservice.application.utils.PagedResult;

public interface IRealEstateCategoryHandler {
    void saveRealEstateCategory(RealEstateCategoryRequestDto realEstateCategoryRequestDto);

    PagedResult<RealEstateCategoryResponseDto> getAllRealEstateCategories(int page, int size, boolean ascending);

}

package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;
import com.powerup.house_microservice.application.handler.IRealEstateCategoryHandler;
import com.powerup.house_microservice.application.mapper.request.IRealEstateCategoryRequestMapper;
import com.powerup.house_microservice.application.mapper.response.IRealEstateCategoryResponseMapper;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateCategoryHandler implements IRealEstateCategoryHandler {

    private final IRealEstateCategoryServicePort realEstateCategoryServicePort;
    private final IRealEstateCategoryRequestMapper realEstateCategoryRequestMapper;
    private final IRealEstateCategoryResponseMapper realEstateCategoryResponseMapper;

    @Override
    public void saveRealEstateCategory(RealEstateCategoryRequestDto realEstateCategoryRequestDto) {
        realEstateCategoryServicePort.saveRealEstateCategory(realEstateCategoryRequestMapper.toRealEstateCategoryModel(realEstateCategoryRequestDto));
    }

    @Override
    public List<RealEstateCategoryResponseDto> getAllRealEstateCategories(int pageNumber, int pageSize) {
        return realEstateCategoryResponseMapper.toRealEstateCategoryDtoList(realEstateCategoryServicePort.getAllRealEstateCategories(pageNumber, pageSize));
    }
}

package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryDto;
import com.powerup.house_microservice.application.handler.IRealEstateCategoryHandler;
import com.powerup.house_microservice.application.mapper.request.IRealEstateResponseMapper;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealEstateCategoryHandler implements IRealEstateCategoryHandler {

    private final IRealEstateCategoryServicePort realEstateCategoryServicePort;
    private final IRealEstateResponseMapper realEstateResponseMapper;

    @Override
    public void saveRealEstateCategory(RealEstateCategoryDto realEstateCategoryDto) {
        realEstateCategoryServicePort.saveRealEstateCategory(realEstateResponseMapper.toRealEstateCategoryModel(realEstateCategoryDto));
    }
}

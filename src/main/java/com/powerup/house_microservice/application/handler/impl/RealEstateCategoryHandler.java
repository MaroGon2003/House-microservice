package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryDto;
import com.powerup.house_microservice.application.handler.IRealEstateCategoryHandler;
import com.powerup.house_microservice.application.mapper.request.IRealEstateCategoryRequestMapper;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class RealEstateCategoryHandler implements IRealEstateCategoryHandler {

    private final IRealEstateCategoryServicePort realEstateCategoryServicePort;
    private final IRealEstateCategoryRequestMapper realEstateCategoryRequestMapper;

    public RealEstateCategoryHandler(IRealEstateCategoryServicePort realEstateCategoryServicePort, IRealEstateCategoryRequestMapper realEstateCategoryRequestMapper) {
        this.realEstateCategoryServicePort = realEstateCategoryServicePort;
        this.realEstateCategoryRequestMapper = realEstateCategoryRequestMapper;
    }

    @Override
    public void saveRealEstateCategory(RealEstateCategoryDto realEstateCategoryDto) {
        realEstateCategoryServicePort.saveRealEstateCategory(realEstateCategoryRequestMapper.toRealEstateCategoryModel(realEstateCategoryDto));
    }
}

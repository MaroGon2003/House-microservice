package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.RealEstateRequestDto;
import com.powerup.house_microservice.application.handler.IRealEstateHandler;
import com.powerup.house_microservice.application.mapper.request.IRealEstateRequestMapper;
import com.powerup.house_microservice.domain.api.IRealEstateServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealEstateHandler implements IRealEstateHandler {

    private final IRealEstateServicePort realEstateServicePort;
    private final IRealEstateRequestMapper realEstateRequestMapper;
    @Override
    public void createRealEstate(RealEstateRequestDto realEstateRequestDto) {

        realEstateServicePort.createRealEstate(realEstateRequestMapper.toRealEstateModel(realEstateRequestDto));

    }
}

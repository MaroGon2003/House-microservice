package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.handler.ICityHandler;
import com.powerup.house_microservice.application.mapper.request.ICityRequestMapper;
import com.powerup.house_microservice.domain.api.ICityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityHandler implements ICityHandler {

    private final ICityServicePort cityServicePort;
    private final ICityRequestMapper cityRequestMapper;

    @Override
    public void create(CityRequestDto city, Long stateId) {

        cityServicePort.create(cityRequestMapper.toCityModel(city), stateId);

    }
}

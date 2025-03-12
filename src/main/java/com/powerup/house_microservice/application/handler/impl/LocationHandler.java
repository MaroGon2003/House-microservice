package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import com.powerup.house_microservice.application.mapper.request.ILocationRequestMapper;
import com.powerup.house_microservice.domain.api.ILocationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationHandler implements ILocationHandler {

    private final ILocationServicePort locationServicePort;
    private final ILocationRequestMapper locationRequestMapper;

    @Override
    public void saveState(StateRequestDto locationDto) {
        locationServicePort.saveState(locationRequestMapper.toStateModel(locationDto));
    }

    @Override
    public void saveCity(CityRequestDto city, Long stateId) {
        locationServicePort.saveCity(locationRequestMapper.toCityModel(city), stateId);
    }
}

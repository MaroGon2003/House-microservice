package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.LocationRequestDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import com.powerup.house_microservice.application.mapper.request.ILocationRequestMapper;
import com.powerup.house_microservice.domain.api.ILocatoinServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationHandler implements ILocationHandler {

    private final ILocatoinServicePort locatoinServicePort;
    private final ILocationRequestMapper locationRequestMapper;
    @Override
    public void saveLocation(LocationRequestDto locationRequestDto) {
        locatoinServicePort.saveLocation(locationRequestMapper.toLocationModel(locationRequestDto));
    }
}

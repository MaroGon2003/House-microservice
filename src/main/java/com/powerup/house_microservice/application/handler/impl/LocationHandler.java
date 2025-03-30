package com.powerup.house_microservice.application.handler.impl;


import com.powerup.house_microservice.application.dto.request.LocationRequestDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import com.powerup.house_microservice.application.mapper.response.ILocationResponseMapper;
import com.powerup.house_microservice.application.utils.PagedResult;
import com.powerup.house_microservice.domain.api.ILocationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationHandler implements ILocationHandler {

    private final ILocationServicePort locationServicePort;
    private final ILocationResponseMapper locationResponseMapper;

    @Override
    public PagedResult<LocationResponseDto> getLocations(String stateName, String cityName, int page, int size, boolean ascending) {
        return new PagedResult<>(locationResponseMapper.toLocationResponseDtoList(
                locationServicePort.getLocations(stateName, cityName, page, size, ascending)
        ), page, size);
    }

    @Override
    public void create(LocationRequestDto locationRequestDto) {
        locationServicePort.saveLocation(locationRequestDto.getCityId(), locationRequestDto.getNeighborhood());
    }
}

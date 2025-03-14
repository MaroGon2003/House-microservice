package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.dto.response.CityResponseDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.dto.response.StateResponseDto;
import com.powerup.house_microservice.application.handler.ILocationHandler;
import com.powerup.house_microservice.application.mapper.request.ILocationRequestMapper;
import com.powerup.house_microservice.application.mapper.response.ILocationResponseMapper;
import com.powerup.house_microservice.application.utils.PagedResult;
import com.powerup.house_microservice.domain.api.ILocationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationHandler implements ILocationHandler {

    private final ILocationServicePort locationServicePort;
    private final ILocationRequestMapper locationRequestMapper;
    private final ILocationResponseMapper locationResponseMapper;


    @Override
    public void saveState(StateRequestDto locationDto) {
        locationServicePort.saveState(locationRequestMapper.toStateModel(locationDto));
    }

    @Override
    public void saveCity(CityRequestDto city, Long stateId) {
        locationServicePort.saveCity(locationRequestMapper.toCityModel(city), stateId);
    }

    @Override
    public PagedResult<LocationResponseDto> getAllLocationsByCityName(String name, String searchBy, int page, int size, String sortDirection) {

        List<LocationResponseDto> locationResponseDtoList = locationResponseMapper.toLocationResponseDtoList(
                locationServicePort.getAllLocationsByCityNameOrStateName(name, searchBy, page, size, sortDirection)
        );

        Map<StateResponseDto, List<CityResponseDto>> groupedByState = locationResponseDtoList.stream()
                .collect(Collectors.groupingBy(
                        LocationResponseDto::getState,
                        Collectors.flatMapping(
                                location -> location.getCity().stream(),
                                Collectors.toList()
                        )
                ));

        return new PagedResult<>(groupedByState.entrySet().stream()
                .map(entry -> new LocationResponseDto(entry.getKey(), entry.getValue()))
                .toList(), page, size);

    }
}

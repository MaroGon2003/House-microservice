package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.utils.PagedResult;

public interface ILocationHandler {

    void saveState(StateRequestDto locationDto);
    void saveCity(CityRequestDto city, Long stateId);
    PagedResult<LocationResponseDto> getAllLocationsByCityName(String name, String searchBy, int page, int size, String sortDirection);

}

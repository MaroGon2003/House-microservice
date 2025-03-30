package com.powerup.house_microservice.application.handler;


import com.powerup.house_microservice.application.dto.request.LocationRequestDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.utils.PagedResult;

public interface ILocationHandler {

    PagedResult<LocationResponseDto> getLocations(String stateName, String cityName, int page, int size, boolean ascending);

    void create(LocationRequestDto locationRequestDto);

}

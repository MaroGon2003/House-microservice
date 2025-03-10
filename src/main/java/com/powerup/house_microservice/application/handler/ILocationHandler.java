package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.LocationRequestDto;

public interface ILocationHandler {

    void saveLocation(LocationRequestDto locationRequestDto);

}

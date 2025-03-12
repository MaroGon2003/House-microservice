package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.dto.request.StateRequestDto;

public interface ILocationHandler {


    void saveState(StateRequestDto locationDto);

    void saveCity(CityRequestDto city, Long stateId);

}

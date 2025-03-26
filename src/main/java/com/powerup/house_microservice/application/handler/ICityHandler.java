package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;

public interface ICityHandler {

    void create(CityRequestDto city, Long stateId);

}

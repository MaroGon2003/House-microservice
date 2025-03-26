package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.CityModel;

public interface ICityServicePort {
    void create(CityModel city, Long stateId);

}

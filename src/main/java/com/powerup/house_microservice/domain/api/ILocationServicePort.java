package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.StateModel;

public interface ILocationServicePort {

    void saveState(StateModel state);

    void saveCity(CityModel city, Long stateId);

}

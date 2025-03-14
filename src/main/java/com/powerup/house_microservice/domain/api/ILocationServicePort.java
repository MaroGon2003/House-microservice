package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;

import java.util.List;

public interface ILocationServicePort {

    void saveState(StateModel state);
    void saveCity(CityModel city, Long stateId);
    List<LocationModel> getAllLocationsByCityNameOrStateName(String name, String searchBy, int page, int size, String sortDirection);


}

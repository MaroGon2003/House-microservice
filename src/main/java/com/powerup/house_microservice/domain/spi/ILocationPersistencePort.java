package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;

import java.util.List;

public interface ILocationPersistencePort {

    void saveState(StateModel state);
    CityModel saveCity(CityModel city);
    boolean existStateById(Long stateId);
    StateModel getStateById(Long stateId);
    void saveLocation(LocationModel location);
    boolean existStateByName(String stateName);
    boolean existStateAndCity(String cityName, Long stateId);
    boolean existCityByName(String cityName);
    List<LocationModel> getAllLocationsByCityName(String cityName, int page, int size, String sortBy, String sortDirection);
    List<LocationModel> getAllLocationsByStateName(String stateName, int page, int size, String sortBy, String sortDirection);

}

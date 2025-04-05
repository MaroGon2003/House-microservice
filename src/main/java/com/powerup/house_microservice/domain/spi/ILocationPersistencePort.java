package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.LocationModel;


import java.util.List;


public interface ILocationPersistencePort {

    void saveLocation(LocationModel location);
    boolean existStateAndCity(String cityName, Long stateId);
    List<LocationModel> getAllLocationsByCityName(String cityName, int page, int size, String sortDirection);
    List<LocationModel> getAllLocationsByStateName(String stateName, int page, int size, String sortDirection);
    List<LocationModel> getAllLocationsByStateAndCityName(String stateName, String cityName, int page, int size, String sortDirection);
    List<LocationModel> getAllLocations(int page, int size, String sortDirection);
    LocationModel getLocationById(Long locationId);

}

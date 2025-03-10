package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.LocationModel;

public interface ILocationPersistencePort {

    void saveLocation(LocationModel location);

    boolean existSLocationByStateAndCity(String state, String city);

}

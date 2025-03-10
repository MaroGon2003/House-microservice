package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ILocatoinServicePort;
import com.powerup.house_microservice.domain.exception.LocationAlreadyExistException;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.utils.MessageConstants;

public class LocationUseCase implements ILocatoinServicePort {

    private final ILocationPersistencePort locationPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void saveLocation(LocationModel location) {

        if (location == null) {
            throw new IllegalArgumentException(MessageConstants.LOCATION_NOT_NULL);
        }

        if(location.getState().isEmpty() || location.getCity().isEmpty()){
            throw new IllegalArgumentException(MessageConstants.LOCATION_NOT_NULL);
        }

        if(locationPersistencePort.existSLocationByStateAndCity(location.getState(), location.getCity())){
            throw new LocationAlreadyExistException(MessageConstants.LOCATION_ALREADY_EXISTS);
        }

        locationPersistencePort.saveLocation(location);

    }
}

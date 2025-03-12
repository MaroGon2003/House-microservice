package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.exception.LocationAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.utils.MessageConstants;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void saveState(StateModel state) {

        if(locationPersistencePort.existStateByName(state.getName())){
            throw new StateAlreadyExistException(MessageConstants.STATE_ALREADY_EXIST);
        }

        locationPersistencePort.saveState(state);
    }

    @Override
    public void saveCity(CityModel city, Long stateId) {

        if(!locationPersistencePort.existStateById(stateId)){
            throw new StateNotFoundException(MessageConstants.STATE_NOT_FOUND);
        }

        if(locationPersistencePort.existStateAndCity(city.getName(), stateId)){
            throw new LocationAlreadyExistException(MessageConstants.LOCATION_ALREADY_EXIST);
        }

        StateModel stateSaved = locationPersistencePort.getStateById(stateId);
        CityModel citySaved = locationPersistencePort.saveCity(city);

        saveLocation(citySaved,stateSaved);
    }

    public void saveLocation(CityModel city, StateModel state) {

        if (city == null || state == null) {
            throw new IllegalArgumentException(MessageConstants.STATE_OR_CITY_NULL);
        }

        LocationModel location = new LocationModel(city, state);

        locationPersistencePort.saveLocation(location);

    }

}

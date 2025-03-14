package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.exception.LocationAlreadyExistException;
import com.powerup.house_microservice.domain.exception.LocationNotFoundException;
import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.MessageConstants;
import com.powerup.house_microservice.domain.utils.PaginationValidator;

import java.util.List;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void saveState(StateModel state) {

        if(locationPersistencePort.existStateByName(state.getName())){
            throw new StateAlreadyExistException(ErrorMessages.STATE_ALREADY_EXIST);
        }

        locationPersistencePort.saveState(state);
    }

    @Override
    public void saveCity(CityModel city, Long stateId) {

        StateModel stateSaved = locationPersistencePort.getStateById(stateId);

        if(stateSaved == null){
            throw new StateNotFoundException(ErrorMessages.STATE_NOT_FOUND);
        }

        if(locationPersistencePort.existStateAndCity(city.getName(), stateId)){
            throw new LocationAlreadyExistException(ErrorMessages.LOCATION_ALREADY_EXIST);
        }

        CityModel citySaved = locationPersistencePort.saveCity(city);

        saveLocation(citySaved,stateSaved);
    }

    @Override
    public List<LocationModel> getAllLocationsByCityNameOrStateName(String name, String searchBy, int page, int size, String sortDirection) {

        PaginationValidator.validatePaginationParameters(page, size, searchBy, sortDirection);

        List<LocationModel> locationModelList = switch (searchBy.toLowerCase()) {
            case MessageConstants.SEARCH_BY_CITY -> locationPersistencePort.getAllLocationsByCityName(name, page, size, searchBy, sortDirection);
            case MessageConstants.SEARCH_BY_STATE -> locationPersistencePort.getAllLocationsByStateName(name, page, size, searchBy, sortDirection);
            default -> throw new IllegalArgumentException(ErrorMessages.INVALID_SEARCH_BY);
        };

        if (locationModelList.isEmpty()) {
            throw new LocationNotFoundException(ErrorMessages.LOCATION_NOT_FOUND);
        }

        return locationModelList;

    }

    public void saveLocation(CityModel city, StateModel state) {

        if (city == null || state == null) {
            throw new IllegalArgumentException(ErrorMessages.STATE_OR_CITY_NULL);
        }

        LocationModel location = new LocationModel(city, state);

        locationPersistencePort.saveLocation(location);

    }

}

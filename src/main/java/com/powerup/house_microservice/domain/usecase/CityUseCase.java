package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ICityServicePort;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.model.CityModel;

import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ICityPersistencePort;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.StateCityValidationUtils;

public class CityUseCase implements ICityServicePort {

    private final ICityPersistencePort cityPersistencePort;
    private final StateUseCase stateUseCase;

    public CityUseCase(ICityPersistencePort cityPersistencePort, StateUseCase stateUseCase) {
        this.cityPersistencePort = cityPersistencePort;
        this.stateUseCase = stateUseCase;
    }
    @Override
    public void create(CityModel city, Long stateId) {

        StateCityValidationUtils.validateName(city.getName());
        StateCityValidationUtils.validateDescription(city.getDescription());

        StateModel state = stateUseCase.getStateById(stateId);

        if(state == null){
            throw new StateNotFoundException(ErrorMessages.STATE_NOT_FOUND);
        }

        city.setState(state);
        cityPersistencePort.create(city);

    }

    public CityModel getCityById(Long id) {
        return cityPersistencePort.getCityById(id);
    }

}

package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ICityServicePort;
import com.powerup.house_microservice.domain.api.IStateServicePort;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.model.CityModel;

import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ICityPersistencePort;
import com.powerup.house_microservice.domain.utils.DomainConstants;
import com.powerup.house_microservice.domain.utils.StateCityValidationUtils;

public class CityUseCase implements ICityServicePort {

    private final ICityPersistencePort cityPersistencePort;
    private final IStateServicePort stateServicePort;

    public CityUseCase(ICityPersistencePort cityPersistencePort, IStateServicePort stateServicePort) {
        this.cityPersistencePort = cityPersistencePort;
        this.stateServicePort = stateServicePort;
    }
    @Override
    public void create(CityModel city, Long stateId) {

        StateCityValidationUtils.validateName(city.getName());
        StateCityValidationUtils.validateDescription(city.getDescription());

        StateModel state = stateServicePort.getStateById(stateId);

        if(state == null){
            throw new StateNotFoundException(DomainConstants.STATE_NOT_FOUND, stateId);
        }

        city.setState(state);
        cityPersistencePort.create(city);

    }

    @Override
    public CityModel getCityById(Long id) {
        return cityPersistencePort.getCityById(id);
    }

}

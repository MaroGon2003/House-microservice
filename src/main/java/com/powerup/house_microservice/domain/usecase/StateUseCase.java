package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.IStateServicePort;
import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.IStatePersistencePort;
import com.powerup.house_microservice.domain.utils.DomainConstants;
import com.powerup.house_microservice.domain.utils.StateCityValidationUtils;

import java.util.Optional;

public class StateUseCase implements IStateServicePort {

    private final IStatePersistencePort statePersistencePort;

    public StateUseCase(IStatePersistencePort statePersistencePort) {
        this.statePersistencePort = statePersistencePort;
    }

    @Override
    public void create(StateModel state) {

        StateCityValidationUtils.validateName(state.getName());
        StateCityValidationUtils.validateDescription(state.getDescription());

        if(statePersistencePort.existStateByName(state.getName())){
            throw new StateAlreadyExistException(DomainConstants.STATE_ALREADY_EXIST, state.getName());
        }

        statePersistencePort.create(state);

    }

    @Override
    public StateModel getStateById(Long id){

        Optional<StateModel> state = Optional.ofNullable(statePersistencePort.getStateById(id));

        return state.orElseThrow(() -> new StateNotFoundException(DomainConstants.STATE_NOT_FOUND, id));

    }

}

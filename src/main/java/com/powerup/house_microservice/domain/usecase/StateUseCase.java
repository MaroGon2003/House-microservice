package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.IStateServicePort;
import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.IStatePersistencePort;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.StateCityValidationUtils;

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
            throw new StateAlreadyExistException(ErrorMessages.STATE_ALREADY_EXIST);
        }

        statePersistencePort.create(state);

    }

    public StateModel getStateById(Long id){

        StateModel state = statePersistencePort.getStateById(id);

        if(state == null){
            throw new StateNotFoundException(ErrorMessages.STATE_NOT_FOUND);
        }

        return state;

    }

}

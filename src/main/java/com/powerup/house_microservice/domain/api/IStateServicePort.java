package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.StateModel;

public interface IStateServicePort {
    void create(StateModel stateModel);
    StateModel getStateById(Long id);

}

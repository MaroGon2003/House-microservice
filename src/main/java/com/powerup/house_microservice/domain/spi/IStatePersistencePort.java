package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.StateModel;

public interface IStatePersistencePort {

    void create(StateModel stateModel);
    boolean existStateByName(String name);
    boolean existStateById(Long id);
    StateModel getStateById(Long id);

}

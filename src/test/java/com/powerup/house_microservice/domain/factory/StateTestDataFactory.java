package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.StateModel;

public class StateTestDataFactory {

    public static StateModel createStateModel() {
        StateModel state = new StateModel();
        state.setName("Test State");
        state.setDescription("Test Description");
        return state;
    }

    public static StateModel createStateModelWithId(Long id) {
        StateModel state = createStateModel();
        state.setId(id);
        return state;
    }

}

package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.StateModel;

public class StateTestDataFactory {

    public static StateModel createStateModel() {
        return new StateModel(null,"Test State", "Test Description");
    }

    public static StateModel createStateModelWithId(Long id) {
        StateModel state = new StateModel();
        state.setId(id);
        state.setName("Test State");
        state.setDescription("Test Description");
        return state;
    }

}

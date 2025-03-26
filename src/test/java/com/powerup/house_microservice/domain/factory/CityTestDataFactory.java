package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.StateModel;

public class CityTestDataFactory {

    public static CityModel createCityModel() {
        CityModel city = new CityModel();
        city.setName("Test City");
        city.setDescription("Test Description");
        return city;
    }

    public static CityModel createCityModelWithState(StateModel state) {
        CityModel city = createCityModel();
        city.setState(state);
        return city;
    }

    public static StateModel createStateModel() {
        StateModel state = new StateModel();
        state.setId(1L);
        state.setName("Test State");
        state.setDescription("Test Description");
        return state;
    }

}

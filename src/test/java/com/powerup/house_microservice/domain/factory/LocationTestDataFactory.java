package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.StateModel;

public class LocationTestDataFactory {

    public static StateModel createValidStateModel() {
        return new StateModel(1L, "name", "description");
    }

    public static CityModel createValidCityModel() {
        return new CityModel(1L, "name", "description");
    }

}

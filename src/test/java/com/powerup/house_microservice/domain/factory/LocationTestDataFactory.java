package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;

import java.util.List;


public class LocationTestDataFactory {

    public static StateModel createValidStateModel() {
        return new StateModel(1L, "name", "description");
    }

    public static CityModel createValidCityModel() {
        return new CityModel(1L, "name", "description");
    }

    public static LocationModel createValidLocationModel() {
        return new LocationModel(createValidCityModel(), createValidStateModel());
    }

    public static List<LocationModel> createValidLocationModelList() {
        return List.of(createValidLocationModel());
    }

}

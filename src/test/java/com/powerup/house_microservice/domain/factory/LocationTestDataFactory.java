package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;

public class LocationTestDataFactory {

    public static CityModel createValidCityModel() {
        CityModel city = new CityModel();
        city.setId(1L);
        city.setName("Test City");
        city.setDescription("Test Description");
        city.setState(createValidStateModel());
        return city;
    }

    public static StateModel createValidStateModel() {
        StateModel state = new StateModel();
        state.setId(1L);
        state.setName("Test State");
        state.setDescription("Test Description");
        return state;
    }

    public static LocationModel createValidLocationModel() {
        LocationModel location = new LocationModel();
        location.setId(1L);
        location.setCity(createValidCityModel());
        location.setNeighborhood("Test Neighborhood");
        return location;
    }

}

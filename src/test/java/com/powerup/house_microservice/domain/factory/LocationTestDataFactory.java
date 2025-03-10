package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.LocationModel;

public class LocationTestDataFactory {

    public static LocationModel createLocationModel() {
        return new LocationModel(1L, "city", "state");
    }

    public static LocationModel createLocationModelWithOutCity() {
        return new LocationModel(1L, "", "state");
    }

}

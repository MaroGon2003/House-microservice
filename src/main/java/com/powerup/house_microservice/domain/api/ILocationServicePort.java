package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.LocationModel;


import java.util.List;

public interface ILocationServicePort {
    List<LocationModel> getLocations(String stateName, String cityName, int page, int size, String sortDirection);

    void saveLocation(Long cityId, String neighborhood);


}

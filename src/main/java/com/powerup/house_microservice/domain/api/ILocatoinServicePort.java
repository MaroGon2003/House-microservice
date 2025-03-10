package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.LocationModel;

public interface ILocatoinServicePort {

    void saveLocation(LocationModel location);

}

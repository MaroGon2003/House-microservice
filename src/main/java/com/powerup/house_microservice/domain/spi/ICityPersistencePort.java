package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.CityModel;

public interface ICityPersistencePort {
    void create(CityModel city);

    CityModel getCityById(Long id);

}

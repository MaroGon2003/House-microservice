package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.RealEstateModel;

public interface IRealEstateServicePort {

    void createRealEstate(RealEstateModel realEstateModel);

}

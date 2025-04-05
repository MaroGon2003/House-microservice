package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.RealEstateModel;

public interface IRealEstatePersistencePort {

    void createRealEstate(RealEstateModel realEstateModel);

}

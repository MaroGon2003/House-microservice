package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.RealEstateFilter;
import com.powerup.house_microservice.domain.model.RealEstateModel;

import java.util.List;

public interface IRealEstatePersistencePort {
    void createRealEstate(RealEstateModel realEstateModel);
    List<RealEstateModel> getRealEstatesByFilters(RealEstateFilter filter);
    boolean existsById(Long id);
}

package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;

public interface IRealEstateCategoryPersistencePort {

    void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory);

    boolean existsRealEstateCategoryByName(String name);

}

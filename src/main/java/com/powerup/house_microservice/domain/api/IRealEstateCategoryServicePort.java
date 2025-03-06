package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;

public interface IRealEstateCategoryServicePort {

    void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory);

}

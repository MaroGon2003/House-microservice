package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;

import java.util.List;

public interface IRealEstateCategoryServicePort {

    void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory);
    List<RealEstateCategoryModel> getAllRealEstateCategories(int page, int size, String sortDirection);

}

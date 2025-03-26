package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;

import java.util.List;

public interface IRealEstateCategoryPersistencePort {

    void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory);

    List<RealEstateCategoryModel> getAllRealEstateCategories(int page, int size, String sortDirection);

    boolean existsRealEstateCategoryByName(String name);

}

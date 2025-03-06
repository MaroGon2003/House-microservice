package com.powerup.house_microservice.domain.spi;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;

import java.util.List;

public interface IRealEstateCategoryPersistencePort {

    void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory);

    List<RealEstateCategoryModel> getAllRealEstateCategories(int pageNumber, int pageSize);

    boolean existsRealEstateCategoryByName(String name);

}

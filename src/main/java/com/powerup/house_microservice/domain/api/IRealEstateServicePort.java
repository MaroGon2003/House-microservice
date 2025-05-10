package com.powerup.house_microservice.domain.api;

import com.powerup.house_microservice.domain.model.RealEstateModel;

import java.math.BigDecimal;
import java.util.List;

public interface IRealEstateServicePort {

    void createRealEstate(RealEstateModel realEstateModel);

    List<RealEstateModel> getRealEstates(String stateName, String cityName, Long categoryId,
                                         Integer rooms, Integer bathrooms, BigDecimal minPrice,BigDecimal maxPrice,
                                         int page, int size, boolean ascending);

    boolean existsById(Long id);

}

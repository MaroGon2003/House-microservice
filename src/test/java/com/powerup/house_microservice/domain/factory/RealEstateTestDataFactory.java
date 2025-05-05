package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.ListingStatus;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.model.RealEstateModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RealEstateTestDataFactory {

    public static RealEstateModel createRealEstateModelWithActiveFrom() {
        return new RealEstateModel("name", "description", new RealEstateCategoryModel(1L, null, null), 2, 2, null, new LocationModel(1L, null, null), LocalDate.now().minusDays(1));
    }

    public static RealEstateModel createValidRealEstateModel() {
        RealEstateModel realEstateModel = new RealEstateModel();
        realEstateModel.setActiveFrom(LocalDate.now().plusDays(1));
        realEstateModel.setLocation(new LocationModel(1L, null, null));
        realEstateModel.setCategory(new RealEstateCategoryModel(1L, null, null));
        realEstateModel.setName("name");
        realEstateModel.setDescription("description");
        realEstateModel.setRoomsCount(2);
        realEstateModel.setBathroomsCount(2);
        realEstateModel.setPrice(BigDecimal.valueOf(300000000));
        realEstateModel.setCreatedOn(LocalDate.now());
        realEstateModel.setStatus(ListingStatus.PUBLISHED);
        return realEstateModel;
    }


    public static RealEstateModel validRealEstateInBd() {
        RealEstateModel realEstateModel = new RealEstateModel();
        realEstateModel.setId(1L);
        realEstateModel.setName("name");
        realEstateModel.setDescription("description");
        realEstateModel.setCategory(RealEstateCategoryTestDataFactory.createRealEstateCategoryModel());
        realEstateModel.setRoomsCount(2);
        realEstateModel.setBathroomsCount(2);
        realEstateModel.setPrice(BigDecimal.valueOf(300000000));
        realEstateModel.setLocation(LocationTestDataFactory.createValidLocationModel());
        realEstateModel.setActiveFrom(LocalDate.now().plusDays(1));
        return realEstateModel;
    }

    public static List<RealEstateModel> createRealEstateModelList() {
        RealEstateModel realEstate1 = validRealEstateInBd();
        RealEstateModel realEstate2 = validRealEstateInBd();
        realEstate2.setId(2L);
        return List.of(realEstate1, realEstate2);
    }

}

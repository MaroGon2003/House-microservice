package com.powerup.house_microservice.domain.factory;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;

import java.util.List;

public class RealEstateCategoryTestDataFactory {

    public static RealEstateCategoryModel createRealEstateCategoryModel() {

        RealEstateCategoryModel realEstateCategoryModel = new RealEstateCategoryModel();
        realEstateCategoryModel.setId(1L);
        realEstateCategoryModel.setName("name");
        realEstateCategoryModel.setDescription("description");
        return realEstateCategoryModel;
    }

    public static RealEstateCategoryModel createRealEstateCategoryModelWithOutName() {
        return new RealEstateCategoryModel(1L, "", "description");
    }

    public static List<RealEstateCategoryModel> createRealEstateCategoryModelList() {
        return List.of(
                new RealEstateCategoryModel(1L, "name1", "description1"),
                new RealEstateCategoryModel(2L, "name2", "description2")
        );
    }

}

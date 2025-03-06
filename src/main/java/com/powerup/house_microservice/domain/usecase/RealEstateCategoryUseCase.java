package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.exception.RealEstateCategoryAlreadyExistException;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.utils.MessageConstants;

import java.util.List;

public class RealEstateCategoryUseCase implements IRealEstateCategoryServicePort {

    private final IRealEstateCategoryPersistencePort realEstateCategoryPersistencePort;

    public RealEstateCategoryUseCase(IRealEstateCategoryPersistencePort realEstateCategoryPersistencePort) {
        this.realEstateCategoryPersistencePort = realEstateCategoryPersistencePort;
    }

    @Override
    public void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory) {

        if(realEstateCategory == null|| realEstateCategory.getName().isEmpty()){
            throw new IllegalArgumentException(MessageConstants.REAL_ESTATE_CATEGORY_NOT_NULL);
        }

        if(realEstateCategoryPersistencePort.existsRealEstateCategoryByName(realEstateCategory.getName())){
            throw new RealEstateCategoryAlreadyExistException(MessageConstants.REAL_ESTATE_CATEGORY_ALREADY_EXISTS);
        }

        //validar administrador

        realEstateCategoryPersistencePort.saveRealEstateCategory(realEstateCategory);

    }

    @Override
    public List<RealEstateCategoryModel> getAllRealEstateCategories(int pageNumber, int pageSize) {
        List<RealEstateCategoryModel> realEstateCategoryList = realEstateCategoryPersistencePort.getAllRealEstateCategories(pageNumber, pageSize);
        if(realEstateCategoryList.isEmpty()){
            throw new IllegalArgumentException(MessageConstants.REAL_ESTATE_CATEGORY_NOT_FOUND);
        }
        return realEstateCategoryList;
    }
}

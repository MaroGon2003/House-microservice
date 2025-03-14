package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.exception.RealEstateCategoryAlreadyExistException;
import com.powerup.house_microservice.domain.exception.RealEstateCategoryNotFoundException;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.PaginationValidator;

import java.util.List;

public class RealEstateCategoryUseCase implements IRealEstateCategoryServicePort {

    private final IRealEstateCategoryPersistencePort realEstateCategoryPersistencePort;

    public RealEstateCategoryUseCase(IRealEstateCategoryPersistencePort realEstateCategoryPersistencePort) {
        this.realEstateCategoryPersistencePort = realEstateCategoryPersistencePort;
    }

    @Override
    public void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory) {

        if(realEstateCategory == null|| realEstateCategory.getName().isEmpty()){
            throw new IllegalArgumentException(ErrorMessages.REAL_ESTATE_CATEGORY_NOT_NULL);
        }

        if(realEstateCategoryPersistencePort.existsRealEstateCategoryByName(realEstateCategory.getName())){
            throw new RealEstateCategoryAlreadyExistException(ErrorMessages.REAL_ESTATE_CATEGORY_ALREADY_EXISTS);
        }

        realEstateCategoryPersistencePort.saveRealEstateCategory(realEstateCategory);

    }
    @Override
    public List<RealEstateCategoryModel> getAllRealEstateCategories(int page, int size, String sortBy, String sortDirection) {

        PaginationValidator.validatePaginationParameters(page, size, sortBy, sortDirection);

        List<RealEstateCategoryModel> realEstateCategoryModelList = realEstateCategoryPersistencePort.getAllRealEstateCategories(page, size, sortBy, sortDirection);

        if (realEstateCategoryModelList.isEmpty()) {
            throw new RealEstateCategoryNotFoundException(ErrorMessages.REAL_ESTATE_CATEGORY_NOT_FOUND);
        }

        return realEstateCategoryModelList;

    }
}

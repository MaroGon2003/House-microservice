package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.exception.RealEstateCategoryAlreadyExistException;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.MessageConstants;
import com.powerup.house_microservice.domain.utils.PaginationValidator;
import com.powerup.house_microservice.domain.utils.RealEstateValidationUtil;

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

        RealEstateValidationUtil.validateName(realEstateCategory.getName());
        RealEstateValidationUtil.validateDescription(realEstateCategory.getDescription());

        if(realEstateCategoryPersistencePort.existsRealEstateCategoryByName(realEstateCategory.getName())){
            throw new RealEstateCategoryAlreadyExistException(ErrorMessages.REAL_ESTATE_CATEGORY_ALREADY_EXISTS, realEstateCategory.getName());
        }

        realEstateCategoryPersistencePort.saveRealEstateCategory(realEstateCategory);

    }
    @Override
    public List<RealEstateCategoryModel> getAllRealEstateCategories(int page, int size, boolean ascending) {

        String sortDirection = ascending ? MessageConstants.ASC : MessageConstants.DESC;

        PaginationValidator.validatePaginationParameters(page, size, sortDirection);

        return realEstateCategoryPersistencePort.getAllRealEstateCategories(page, size, sortDirection);

    }
}

package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.RealEstateCategoryAlreadyExistException;
import com.powerup.house_microservice.domain.factory.RealEstateCategoryTestDataFactory;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.useCase.RealEstateCategoryUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RealEstateCategoryUseCaseTest {

    @InjectMocks
    private RealEstateCategoryUseCase realEstateCategoryUseCase;

    @Mock
    private IRealEstateCategoryPersistencePort realEstateCategoryPersistencePort;

    @Test
    void When_SaveRealEstateCategory_Expect_Success() {
        RealEstateCategoryModel realEstateCategoryModel = RealEstateCategoryTestDataFactory.createRealEstateCategoryModel();
        realEstateCategoryUseCase.saveRealEstateCategory(realEstateCategoryModel);
        verify(realEstateCategoryPersistencePort).saveRealEstateCategory(realEstateCategoryModel);
    }

    @Test
    void When_SaveRealEstateCategory_Expect_RealEstateCategoryAlreadyExistException() {
        RealEstateCategoryModel realEstateCategoryModel = RealEstateCategoryTestDataFactory.createRealEstateCategoryModel();
        when(realEstateCategoryPersistencePort.existsRealEstateCategoryByName(realEstateCategoryModel.getName())).thenReturn(true);
        assertThrows(RealEstateCategoryAlreadyExistException.class, () -> realEstateCategoryUseCase.saveRealEstateCategory(realEstateCategoryModel));
    }

    @Test
    void When_SaveRealEstateCategory_Expect_IllegalArgumentException() {
        RealEstateCategoryModel realEstateCategoryModel = RealEstateCategoryTestDataFactory.createRealEstateCategoryModelWithOutName();
        assertThrows(IllegalArgumentException.class, () -> realEstateCategoryUseCase.saveRealEstateCategory(realEstateCategoryModel));
    }

    @Test
    void When_GetAllRealEstateCategories_Expect_Success() {
        List<RealEstateCategoryModel> realEstateCategoryModelList = RealEstateCategoryTestDataFactory.createRealEstateCategoryModelList();
        when(realEstateCategoryPersistencePort.getAllRealEstateCategories(0, 10)).thenReturn(realEstateCategoryModelList);
        assertNotNull(realEstateCategoryUseCase.getAllRealEstateCategories(0, 10));
    }

    @Test
    void When_GetAllRealEstateCategories_Expect_IllegalArgumentException() {
        when(realEstateCategoryPersistencePort.getAllRealEstateCategories(0, 10)).thenReturn(List.of());
        assertThrows(IllegalArgumentException.class, () -> realEstateCategoryUseCase.getAllRealEstateCategories(0, 10));
    }

}

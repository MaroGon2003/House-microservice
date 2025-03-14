package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.RealEstateCategoryAlreadyExistException;
import com.powerup.house_microservice.domain.exception.RealEstateCategoryNotFoundException;
import com.powerup.house_microservice.domain.factory.RealEstateCategoryTestDataFactory;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.usecase.RealEstateCategoryUseCase;
import com.powerup.house_microservice.domain.utils.PaginationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
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


    private int page;
    private int size;
    private String sortBy;
    private String sortDirection;

    @BeforeEach
    void setup() {
        page = 0;
        size = 10;
        sortBy = "name";
        sortDirection = "asc";
    }

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
        when(realEstateCategoryPersistencePort.getAllRealEstateCategories(page, size, sortBy, sortDirection)).thenReturn(realEstateCategoryModelList);

        List<RealEstateCategoryModel> realEstateCategoryModelListResult = realEstateCategoryUseCase.getAllRealEstateCategories(page, size, sortBy, sortDirection);

        assertEquals(realEstateCategoryModelList, realEstateCategoryModelListResult);
    }

    @Test
    void When_GetAllRealEstateCategories_Expect_RealEstateCategoryNotFoundException() {
        when(realEstateCategoryPersistencePort.getAllRealEstateCategories(page, size, sortBy, sortDirection)).thenReturn(List.of());
        assertThrows(RealEstateCategoryNotFoundException.class, () -> realEstateCategoryUseCase.getAllRealEstateCategories(page, size, sortBy, sortDirection));
    }

    @Test
    void When_GetAllRealEstateCategories_Expect_PaginationValidatorCalled() {
        try (MockedStatic<PaginationValidator> mockedValidator = mockStatic(PaginationValidator.class)) {
            List<RealEstateCategoryModel> realEstateCategoryModelList = RealEstateCategoryTestDataFactory.createRealEstateCategoryModelList();
            when(realEstateCategoryPersistencePort.getAllRealEstateCategories(page, size, sortBy, sortDirection)).thenReturn(realEstateCategoryModelList);

            realEstateCategoryUseCase.getAllRealEstateCategories(page, size, sortBy, sortDirection);

            mockedValidator.verify(() -> PaginationValidator.validatePaginationParameters(page, size, sortBy, sortDirection));
        }
    }

}

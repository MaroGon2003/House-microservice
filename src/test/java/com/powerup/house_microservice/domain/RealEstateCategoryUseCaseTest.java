package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.RealEstateCategoryAlreadyExistException;
import com.powerup.house_microservice.domain.factory.RealEstateCategoryTestDataFactory;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.usecase.RealEstateCategoryUseCase;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.PaginationValidator;
import com.powerup.house_microservice.domain.utils.RealEstateValidationUtil;
import com.powerup.house_microservice.domain.utils.ValidationConstants;
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
    private boolean ascending;
    private String sortDirection;

    @BeforeEach
    void setup() {
        page = 0;
        size = 10;
        ascending = true;
        sortDirection = "ASC";
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
        when(realEstateCategoryPersistencePort.getAllRealEstateCategories(page, size, sortDirection)).thenReturn(realEstateCategoryModelList);

        List<RealEstateCategoryModel> realEstateCategoryModelListResult = realEstateCategoryUseCase.getAllRealEstateCategories(page, size, ascending);

        assertEquals(realEstateCategoryModelList, realEstateCategoryModelListResult);
    }

    @Test
    void When_GetAllRealEstateCategories_Expect_PaginationValidatorCalled() {
        try (MockedStatic<PaginationValidator> mockedValidator = mockStatic(PaginationValidator.class)) {
            List<RealEstateCategoryModel> realEstateCategoryModelList = RealEstateCategoryTestDataFactory.createRealEstateCategoryModelList();
            when(realEstateCategoryPersistencePort.getAllRealEstateCategories(page, size, sortDirection)).thenReturn(realEstateCategoryModelList);

            realEstateCategoryUseCase.getAllRealEstateCategories(page, size, ascending);

            mockedValidator.verify(() -> PaginationValidator.validatePaginationParameters(page, size, sortDirection));
        }
    }

    @Test
    void validateName_ShouldThrowException_WhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateName(null)
        );
        assertEquals(ErrorMessages.NAME_CANNOT_BE_BLANK, exception.getMessage());
    }

    @Test
    void validateName_ShouldThrowException_WhenNameIsBlank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateName("")
        );
        assertEquals(ErrorMessages.NAME_CANNOT_BE_BLANK, exception.getMessage());
    }

    @Test
    void validateName_ShouldThrowException_WhenNameIsTooShort() {
        String shortName = "a"; // Assuming NAME_MIN_LENGTH is greater than 1
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateName(shortName)
        );
        assertEquals(ErrorMessages.NAME_LENGTH_ERROR, exception.getMessage());
    }

    @Test
    void validateName_ShouldThrowException_WhenNameIsTooLong() {
        String longName = "a".repeat(ValidationConstants.NAME_MAX_LENGTH + 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateName(longName)
        );
        assertEquals(ErrorMessages.NAME_LENGTH_ERROR, exception.getMessage());
    }

    @Test
    void validateName_ShouldNotThrowException_WhenNameIsValid() {
        String validName = "Valid Name";
        assertDoesNotThrow(() -> RealEstateValidationUtil.validateName(validName));
    }

    @Test
    void validateDescription_ShouldThrowException_WhenDescriptionIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateDescription(null)
        );
        assertEquals(ErrorMessages.DESCRIPTION_CANNOT_BE_BLANK, exception.getMessage());
    }

    @Test
    void validateDescription_ShouldThrowException_WhenDescriptionIsBlank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateDescription("")
        );
        assertEquals(ErrorMessages.DESCRIPTION_CANNOT_BE_BLANK, exception.getMessage());
    }

    @Test
    void validateDescription_ShouldThrowException_WhenDescriptionIsTooShort() {
        String shortDescription = "a"; // Assuming DESCRIPTION_MIN_LENGTH is greater than 1
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateDescription(shortDescription)
        );
        assertEquals(ErrorMessages.DESCRIPTION_LENGTH_ERROR, exception.getMessage());
    }

    @Test
    void validateDescription_ShouldThrowException_WhenDescriptionIsTooLong() {
        String longDescription = "a".repeat(ValidationConstants.DESCRIPTION_MAX_LENGTH + 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                RealEstateValidationUtil.validateDescription(longDescription)
        );
        assertEquals(ErrorMessages.DESCRIPTION_LENGTH_ERROR, exception.getMessage());
    }

    @Test
    void validateDescription_ShouldNotThrowException_WhenDescriptionIsValid() {
        String validDescription = "Valid Description";
        assertDoesNotThrow(() -> RealEstateValidationUtil.validateDescription(validDescription));
    }

}

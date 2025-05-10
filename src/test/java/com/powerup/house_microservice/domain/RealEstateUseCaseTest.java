package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.exception.PublicationDateException;
import com.powerup.house_microservice.domain.factory.RealEstateTestDataFactory;
import com.powerup.house_microservice.domain.model.ListingStatus;
import com.powerup.house_microservice.domain.model.RealEstateFilter;
import com.powerup.house_microservice.domain.model.RealEstateModel;
import com.powerup.house_microservice.domain.spi.IRealEstatePersistencePort;
import com.powerup.house_microservice.domain.usecase.RealEstateUseCase;
import com.powerup.house_microservice.domain.utils.DomainConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealEstateUseCaseTest {

    @Mock
    private IRealEstatePersistencePort realEstatePersistencePort;

    @Mock
    private ILocationServicePort locationServicePort;

    @Mock
    private IRealEstateCategoryServicePort realEstateCategoryServicePort;

    @InjectMocks
    private RealEstateUseCase realEstateUseCase;

    @Test
    void createRealEstate_shouldThrowException_whenActiveFromIsInPast() {

        RealEstateModel realEstateModel = RealEstateTestDataFactory.createRealEstateModelWithActiveFrom();

        PublicationDateException exception = assertThrows(PublicationDateException.class, () -> {
            realEstateUseCase.createRealEstate(realEstateModel);
        });

        assertEquals(DomainConstants.PUBLICATION_DATE_NOT_IN_PAST, exception.getMessage());
    }

    @Test
    void createRealEstate_shouldThrowException_whenActiveFromIsMoreThanOneMonthInFuture() {
        RealEstateModel realEstateModel = RealEstateTestDataFactory.createValidRealEstateModel();
        realEstateModel.setActiveFrom(LocalDate.now().plusMonths(DomainConstants.MAX_MONTHS + 1));

        PublicationDateException exception = assertThrows(PublicationDateException.class, () -> {
            realEstateUseCase.createRealEstate(realEstateModel);
        });

        assertEquals(DomainConstants.PUBLICATION_DATE_NOT_MORE_THAN_1_MONTH, exception.getMessage());
    }

    @Test
    void createRealEstate_shouldSetStatusToPublished_whenActiveFromIsToday() {
        RealEstateModel realEstateModel = RealEstateTestDataFactory.createValidRealEstateModel();
        realEstateModel.setActiveFrom(LocalDate.now());

        when(locationServicePort.getLocationById(realEstateModel.getLocation().getId())).thenReturn(realEstateModel.getLocation());
        when(realEstateCategoryServicePort.getRealEstateCategoryById(realEstateModel.getCategory().getId())).thenReturn(realEstateModel.getCategory());

        realEstateUseCase.createRealEstate(realEstateModel);

        assertEquals(ListingStatus.PUBLISHED, realEstateModel.getStatus());
        verify(realEstatePersistencePort, times(1)).createRealEstate(realEstateModel);
    }

    @Test
    void createRealEstate_shouldSetStatusToListingPaused_whenActiveFromIsNotToday() {
        RealEstateModel realEstateModel = RealEstateTestDataFactory.createValidRealEstateModel();
        realEstateModel.setActiveFrom(LocalDate.now().plusDays(1));

        when(locationServicePort.getLocationById(realEstateModel.getLocation().getId())).thenReturn(realEstateModel.getLocation());
        when(realEstateCategoryServicePort.getRealEstateCategoryById(realEstateModel.getCategory().getId())).thenReturn(realEstateModel.getCategory());

        realEstateUseCase.createRealEstate(realEstateModel);

        assertEquals(ListingStatus.LISTING_PAUSED, realEstateModel.getStatus());
        verify(realEstatePersistencePort, times(1)).createRealEstate(realEstateModel);
    }

    @Test
    void getRealEstates_shouldReturnResults_whenValidFilterIsProvided() {

        RealEstateModel realEstateModelBd = RealEstateTestDataFactory.validRealEstateInBd();
        // Arrange
        List<RealEstateModel> expectedResults = RealEstateTestDataFactory.createRealEstateModelList();
        when(realEstatePersistencePort.getRealEstatesByFilters(any(RealEstateFilter.class))).thenReturn(expectedResults);

        // Act
        List<RealEstateModel> results = realEstateUseCase.getRealEstates(
                realEstateModelBd.getLocation().getCity().getState().getName(), // stateName
                realEstateModelBd.getLocation().getCity().getName(), // cityName
                realEstateModelBd.getCategory().getId(),             // categoryId
                realEstateModelBd.getRoomsCount(),                   // rooms
                realEstateModelBd.getBathroomsCount(),               // bathrooms
                BigDecimal.valueOf(100000),                          // minPrice
                BigDecimal.valueOf(200000),                          // maxPrice
                0,                                                   // page
                10,                                                  // size
                true                                                 // ascending
        );

        // Assert
        assertEquals(expectedResults, results);
        verify(realEstatePersistencePort, times(1)).getRealEstatesByFilters(any(RealEstateFilter.class));
    }

    @Test
    void getRealEstates_shouldReturnEmptyList_whenNoResultsAreFound() {
        // Arrange
        RealEstateModel realEstateModelBd = RealEstateTestDataFactory.validRealEstateInBd();

        when(realEstatePersistencePort.getRealEstatesByFilters(any(RealEstateFilter.class))).thenReturn(Collections.emptyList());

        // Act
        List<RealEstateModel> results = realEstateUseCase.getRealEstates(
                realEstateModelBd.getLocation().getCity().getState().getName(), // stateName
                realEstateModelBd.getLocation().getCity().getName(), // cityName
                realEstateModelBd.getCategory().getId(),             // categoryId
                realEstateModelBd.getRoomsCount(),                   // rooms
                realEstateModelBd.getBathroomsCount(),               // bathrooms
                BigDecimal.valueOf(100000),                          // minPrice
                BigDecimal.valueOf(200000),                          // maxPrice
                0,                                                   // page
                10,                                                  // size
                true                                                 // ascending
        );

        // Assert
        assertEquals(0, results.size());
        verify(realEstatePersistencePort, times(1)).getRealEstatesByFilters(any(RealEstateFilter.class));
    }

    @Test
    void getRealEstates_shouldThrowException_whenInvalidPaginationParametersAreProvided() {
        // Arrange
        int invalidPage = -1;
        int size = 10;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            realEstateUseCase.getRealEstates(
                    null, // stateName
                    null, // cityName
                    null, // categoryId
                    null, // rooms
                    null, // bathrooms
                    null, // minPrice
                    null, // maxPrice
                    invalidPage,
                    size,
                    true  // ascending
            );
        });

        verify(realEstatePersistencePort, never()).getRealEstatesByFilters(any(RealEstateFilter.class));
    }

    @Test
    void existsById_shouldReturnFalse_whenIdIsNull() {
        // Act
        boolean result = realEstateUseCase.existsById(null);

        // Assert
        assertFalse(result);
        verify(realEstatePersistencePort, never()).existsById(any());
    }

    @Test
    void existsById_shouldReturnTrue_whenIdExists() {
        // Arrange
        Long validId = 1L;
        when(realEstatePersistencePort.existsById(validId)).thenReturn(true);

        // Act
        boolean result = realEstateUseCase.existsById(validId);

        // Assert
        assertTrue(result);
        verify(realEstatePersistencePort, times(1)).existsById(validId);
    }

    @Test
    void existsById_shouldReturnFalse_whenIdDoesNotExist() {
        // Arrange
        Long invalidId = 2L;
        when(realEstatePersistencePort.existsById(invalidId)).thenReturn(false);

        // Act
        boolean result = realEstateUseCase.existsById(invalidId);

        // Assert
        assertFalse(result);
        verify(realEstatePersistencePort, times(1)).existsById(invalidId);
    }

}

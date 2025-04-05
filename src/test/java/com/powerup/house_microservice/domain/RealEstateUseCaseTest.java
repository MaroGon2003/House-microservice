package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.exception.PublicationDateException;
import com.powerup.house_microservice.domain.factory.RealEstateTestDataFactory;
import com.powerup.house_microservice.domain.model.ListingStatus;
import com.powerup.house_microservice.domain.model.RealEstateModel;
import com.powerup.house_microservice.domain.spi.IRealEstatePersistencePort;
import com.powerup.house_microservice.domain.usecase.RealEstateUseCase;
import com.powerup.house_microservice.domain.utils.DomainConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
}

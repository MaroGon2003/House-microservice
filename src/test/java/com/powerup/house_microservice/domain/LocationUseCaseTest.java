package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.LocationAlreadyExistException;
import com.powerup.house_microservice.domain.factory.LocationTestDataFactory;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.usecase.LocationUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @InjectMocks
    private LocationUseCase locationUseCase;

    @Mock
    private ILocationPersistencePort locationPersistencePort;


    @Test
    void When_SaveLocation_Expect_Success() {
        LocationModel locationModel = LocationTestDataFactory.createLocationModel();
        locationUseCase.saveLocation(locationModel);
        verify(locationPersistencePort).saveLocation(locationModel);
    }

    @Test
    void When_SaveLocation_Expect_IllegalArgumentException() {
        LocationModel locationModel = LocationTestDataFactory.createLocationModelWithOutCity();
        assertThrows(IllegalArgumentException.class, () -> locationUseCase.saveLocation(locationModel));
    }

    @Test
    void When_SaveLocation_Expect_LocationAlreadyExistException() {
        LocationModel locationModel = LocationTestDataFactory.createLocationModel();
        when(locationPersistencePort.existSLocationByStateAndCity(locationModel.getState(), locationModel.getCity())).thenReturn(true);
        assertThrows(LocationAlreadyExistException.class, () -> locationUseCase.saveLocation(locationModel));
    }

    @Test
    void When_SaveLocation_Expect_IllegalArgumentException_Null() {
        LocationModel locationModel = null;
        assertThrows(IllegalArgumentException.class, () -> locationUseCase.saveLocation(locationModel));
    }

}

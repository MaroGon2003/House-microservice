package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.LocationAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.factory.LocationTestDataFactory;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.usecase.LocationUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @InjectMocks
    private LocationUseCase locationUseCase;

    @Mock
    private ILocationPersistencePort locationPersistencePort;


    //State
    @Test
    void When_SaveState_Expect_Success() {
        StateModel stateModel = LocationTestDataFactory.createValidStateModel();
        when(locationPersistencePort.existStateByName(stateModel.getName())).thenReturn(false);
        locationUseCase.saveState(stateModel);
        verify(locationPersistencePort).saveState(stateModel);
    }

    @Test
    void When_saveState_Expect_StateAlreadyExistException() {
        StateModel stateModel = LocationTestDataFactory.createValidStateModel();
        when(locationPersistencePort.existStateByName(stateModel.getName())).thenReturn(true);
        assertThrows(StateAlreadyExistException.class, () -> locationUseCase.saveState(stateModel));
    }

    //City
    @Test
    void When_SaveCity_Expect_Success() {
        CityModel cityModel = LocationTestDataFactory.createValidCityModel();
        StateModel stateModel = LocationTestDataFactory.createValidStateModel();
        Long stateId = 1L;

        when(locationPersistencePort.existStateById(stateId)).thenReturn(true);
        when(locationPersistencePort.existStateAndCity(cityModel.getName(), stateId)).thenReturn(false);
        when(locationPersistencePort.getStateById(stateId)).thenReturn(stateModel);
        when(locationPersistencePort.saveCity(cityModel)).thenReturn(cityModel);

        locationUseCase.saveCity(cityModel, stateId);

        ArgumentCaptor<LocationModel> locationCaptor = ArgumentCaptor.forClass(LocationModel.class);
        verify(locationPersistencePort).saveCity(cityModel);
        verify(locationPersistencePort).saveLocation(locationCaptor.capture());

        LocationModel capturedLocation = locationCaptor.getValue();
        assertEquals(cityModel, capturedLocation.getCity());
        assertEquals(stateModel, capturedLocation.getState());
    }

    @Test
    void When_SaveCity_Expect_StateNotFoundException() {
        CityModel cityModel = LocationTestDataFactory.createValidCityModel();
        Long stateId = 1L;

        when(locationPersistencePort.existStateById(stateId)).thenReturn(false);

        assertThrows(StateNotFoundException.class, () -> locationUseCase.saveCity(cityModel, stateId));
    }

    @Test
    void When_SaveCity_Expect_LocationAlreadyExistException() {
        CityModel cityModel = LocationTestDataFactory.createValidCityModel();
        Long stateId = 1L;

        when(locationPersistencePort.existStateById(stateId)).thenReturn(true);
        when(locationPersistencePort.existStateAndCity(cityModel.getName(), stateId)).thenReturn(true);

        assertThrows(LocationAlreadyExistException.class, () -> locationUseCase.saveCity(cityModel, stateId));
    }

    @Test
    void When_SaveLocation_Expect_Success() {
        CityModel cityModel = LocationTestDataFactory.createValidCityModel();
        StateModel stateModel = LocationTestDataFactory.createValidStateModel();

        locationUseCase.saveLocation(cityModel, stateModel);

        ArgumentCaptor<LocationModel> locationCaptor = ArgumentCaptor.forClass(LocationModel.class);
        verify(locationPersistencePort).saveLocation(locationCaptor.capture());

        LocationModel capturedLocation = locationCaptor.getValue();
        assertEquals(cityModel, capturedLocation.getCity());
        assertEquals(stateModel, capturedLocation.getState());
    }

}

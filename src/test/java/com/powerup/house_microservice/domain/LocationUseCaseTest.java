package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.LocationAlreadyExistException;
import com.powerup.house_microservice.domain.exception.LocationNotFoundException;
import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.factory.LocationTestDataFactory;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.usecase.LocationUseCase;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.PaginationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @InjectMocks
    private LocationUseCase locationUseCase;

    @Mock
    private ILocationPersistencePort locationPersistencePort;

    private String name;
    private String searchBy;
    private int page;
    private int size;
    private String sortDirection;

    @BeforeEach
    void setup() {
        name = "antioquia";
        searchBy = "state";
        page = 0;
        size = 10;
        sortDirection = "asc";
    }

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

        when(locationPersistencePort.getStateById(stateId)).thenReturn(stateModel);
        when(locationPersistencePort.existStateAndCity(cityModel.getName(), stateId)).thenReturn(false);
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

        when(locationPersistencePort.getStateById(stateId)).thenReturn(null);

        assertThrows(StateNotFoundException.class, () -> locationUseCase.saveCity(cityModel, stateId));
    }

    @Test
    void When_SaveCity_Expect_LocationAlreadyExistException() {
        CityModel cityModel = LocationTestDataFactory.createValidCityModel();
        StateModel stateModel = LocationTestDataFactory.createValidStateModel();
        Long stateId = 1L;

        when(locationPersistencePort.getStateById(stateId)).thenReturn(stateModel);
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

    @Test
    void When_GetAllLocationsByCityNameOrStateName_Expect_PaginationValidatorCalled() {

        searchBy = "city";

        try (MockedStatic<PaginationValidator> mockedValidator = mockStatic(PaginationValidator.class)) {
            List<LocationModel> locationModelList = List.of(new LocationModel());
            when(locationPersistencePort.getAllLocationsByCityName(name, page, size, searchBy, sortDirection)).thenReturn(locationModelList);

            locationUseCase.getAllLocationsByCityNameOrStateName(name, searchBy, page, size, sortDirection);

            mockedValidator.verify(() -> PaginationValidator.validatePaginationParameters(page, size, searchBy, sortDirection));
        }
    }

    @Test
    void When_GetAllLocationsByCityName_Expect_Success() {
        searchBy = "city";
        List<LocationModel> locationModelList = List.of(new LocationModel());

        when(locationPersistencePort.getAllLocationsByCityName(name, page, size, searchBy, sortDirection)).thenReturn(locationModelList);

        List<LocationModel> result = locationUseCase.getAllLocationsByCityNameOrStateName(name, searchBy, page, size, sortDirection);

        assertEquals(locationModelList, result);
    }

    @Test
    void When_GetAllLocationsByStateName_Expect_Success() {
        searchBy = "state";
        List<LocationModel> locationModelList = List.of(new LocationModel());

        when(locationPersistencePort.getAllLocationsByStateName(name, page, size, searchBy, sortDirection)).thenReturn(locationModelList);

        List<LocationModel> result = locationUseCase.getAllLocationsByCityNameOrStateName(name, searchBy, page, size, sortDirection);

        assertEquals(locationModelList, result);
    }

    @Test
    void When_GetAllLocationsByCityNameOrStateName_Expect_InvalidSearchByException() {
        searchBy = "invalid";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> locationUseCase.getAllLocationsByCityNameOrStateName(name, searchBy, page, size, sortDirection)
        );

        assertEquals(ErrorMessages.INVALID_SEARCH_BY, exception.getMessage());
    }

    @Test
    void When_GetAllLocationsByCityNameOrStateName_Expect_LocationNotFoundException() {

        searchBy = "city";

        when(locationPersistencePort.getAllLocationsByCityName(name, page, size, searchBy, sortDirection)).thenReturn(List.of());

        LocationNotFoundException exception = assertThrows(
                LocationNotFoundException.class,
                () -> locationUseCase.getAllLocationsByCityNameOrStateName(name, searchBy, page, size, sortDirection)
        );

        assertEquals(ErrorMessages.LOCATION_NOT_FOUND, exception.getMessage());
    }


}

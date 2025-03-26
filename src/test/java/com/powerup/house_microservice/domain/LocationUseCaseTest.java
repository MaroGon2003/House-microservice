package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.*;
import com.powerup.house_microservice.domain.factory.LocationTestDataFactory;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.usecase.CityUseCase;
import com.powerup.house_microservice.domain.usecase.LocationUseCase;
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

    @Mock
    private ILocationPersistencePort locationPersistencePort;

    @Mock
    private CityUseCase cityUseCase;

    @InjectMocks
    private LocationUseCase locationUseCase;

    private String stateName;
    private String cityName;
    private int page;
    private int size;
    private String sortDirection;

    @BeforeEach
    void setup() {
        stateName = "Test State";
        cityName = "Test City";
        page = 0;
        size = 10;
        sortDirection = "asc";
    }

    @Test
    void getLocations_ShouldReturnLocations_WhenStateAndCityProvided() {
        List<LocationModel> locationModelList = List.of(LocationTestDataFactory.createValidLocationModel());

        when(locationPersistencePort.getAllLocationsByStateAndCityName(stateName, cityName, page, size, sortDirection)).thenReturn(locationModelList);

        List<LocationModel> result = locationUseCase.getLocations(stateName, cityName, page, size, sortDirection);

        assertEquals(locationModelList, result);
    }

    @Test
    void getLocations_ShouldReturnLocations_WhenOnlyStateProvided() {
        List<LocationModel> locationModelList = List.of(LocationTestDataFactory.createValidLocationModel());

        when(locationPersistencePort.getAllLocationsByStateName(stateName, page, size, sortDirection)).thenReturn(locationModelList);

        List<LocationModel> result = locationUseCase.getLocations(stateName, null, page, size, sortDirection);

        assertEquals(locationModelList, result);
    }

    @Test
    void getLocations_ShouldReturnLocations_WhenOnlyCityProvided() {
        List<LocationModel> locationModelList = List.of(LocationTestDataFactory.createValidLocationModel());

        when(locationPersistencePort.getAllLocationsByCityName(cityName, page, size, sortDirection)).thenReturn(locationModelList);

        List<LocationModel> result = locationUseCase.getLocations(null, cityName, page, size, sortDirection);

        assertEquals(locationModelList, result);
    }

    @Test
    void getLocations_ShouldReturnAllLocations_WhenNoStateOrCityProvided() {
        List<LocationModel> locationModelList = List.of(LocationTestDataFactory.createValidLocationModel());

        when(locationPersistencePort.getAllLocations(page, size, sortDirection)).thenReturn(locationModelList);

        List<LocationModel> result = locationUseCase.getLocations(null, null, page, size, sortDirection);

        assertEquals(locationModelList, result);
    }

    @Test
    void saveLocation_ShouldThrowException_WhenCityNotFound() {
        Long cityId = 1L;
        String neighborhood = "Test Neighborhood";

        when(cityUseCase.getCityById(cityId)).thenReturn(null);

        assertThrows(CityNotFoundException.class, () -> locationUseCase.saveLocation(cityId, neighborhood));
    }

    @Test
    void saveLocation_ShouldSaveLocation_WhenCityFound() {
        Long cityId = 1L;
        String neighborhood = "Test Neighborhood";
        CityModel city = LocationTestDataFactory.createValidCityModel();

        when(cityUseCase.getCityById(cityId)).thenReturn(city);

        locationUseCase.saveLocation(cityId, neighborhood);

        ArgumentCaptor<LocationModel> locationCaptor = ArgumentCaptor.forClass(LocationModel.class);
        verify(locationPersistencePort).saveLocation(locationCaptor.capture());

        LocationModel capturedLocation = locationCaptor.getValue();
        assertEquals(city, capturedLocation.getCity());
        assertEquals(neighborhood, capturedLocation.getNeighborhood());
    }

    @Test
    void getLocations_ShouldCallPaginationValidator() {
        try (MockedStatic<PaginationValidator> mockedValidator = mockStatic(PaginationValidator.class)) {
            List<LocationModel> locationModelList = List.of(LocationTestDataFactory.createValidLocationModel());
            when(locationPersistencePort.getAllLocations(page, size, sortDirection)).thenReturn(locationModelList);

            locationUseCase.getLocations(null, null, page, size, sortDirection);

            mockedValidator.verify(() -> PaginationValidator.validatePaginationParameters(page, size, sortDirection));
        }
    }
}

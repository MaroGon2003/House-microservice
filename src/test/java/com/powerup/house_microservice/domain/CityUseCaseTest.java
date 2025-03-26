package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.factory.CityTestDataFactory;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ICityPersistencePort;
import com.powerup.house_microservice.domain.usecase.CityUseCase;
import com.powerup.house_microservice.domain.usecase.StateUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityUseCaseTest {

    @Mock
    private ICityPersistencePort cityPersistencePort;

    @Mock
    private StateUseCase stateUseCase;

    @InjectMocks
    private CityUseCase cityUseCase;

    @Test
    void createCity_ShouldThrowException_WhenStateNotFound() {
        CityModel city = CityTestDataFactory.createCityModel();
        Long stateId = 1L;

        when(stateUseCase.getStateById(stateId)).thenReturn(null);

        assertThrows(StateNotFoundException.class, () -> cityUseCase.create(city, stateId));
    }

    @Test
    void createCity_ShouldCreateCity_WhenStateFound() {
        CityModel city = CityTestDataFactory.createCityModel();
        StateModel state = CityTestDataFactory.createStateModel();
        Long stateId = state.getId();

        when(stateUseCase.getStateById(stateId)).thenReturn(state);

        cityUseCase.create(city, stateId);

        verify(cityPersistencePort, times(1)).create(city);
    }

    @Test
    void getCityById_ShouldReturnCity_WhenCityFound() {
        Long cityId = 1L;
        CityModel city = CityTestDataFactory.createCityModelWithState(CityTestDataFactory.createStateModel());

        when(cityPersistencePort.getCityById(cityId)).thenReturn(city);

        CityModel result = cityUseCase.getCityById(cityId);

        assertNotNull(result);
    }
}

package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.factory.StateTestDataFactory;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.IStatePersistencePort;
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
class StateUseCaseTest {

    @Mock
    private IStatePersistencePort statePersistencePort;

    @InjectMocks
    private StateUseCase stateUseCase;

    @Test
    void createState_ShouldThrowException_WhenStateAlreadyExists() {

        StateModel state = StateTestDataFactory.createStateModel();

        when(statePersistencePort.existStateByName(state.getName())).thenReturn(true);

        assertThrows(StateAlreadyExistException.class, () -> stateUseCase.create(state));
    }

    @Test
    void createState_ShouldCreateState_WhenStateDoesNotExist() {

        StateModel state = StateTestDataFactory.createStateModel();

        when(statePersistencePort.existStateByName(state.getName())).thenReturn(false);

        stateUseCase.create(state);

        verify(statePersistencePort, times(1)).create(state);
    }

    @Test
    void getStateById_ShouldThrowException_WhenStateNotFound() {
        Long stateId = 1L;

        when(statePersistencePort.getStateById(stateId)).thenReturn(null);

        assertThrows(StateNotFoundException.class, () -> stateUseCase.getStateById(stateId));
    }

    @Test
    void getStateById_ShouldReturnState_WhenStateFound() {
        Long stateId = 1L;

        StateModel state = StateTestDataFactory.createStateModelWithId(stateId);

        when(statePersistencePort.getStateById(stateId)).thenReturn(state);

        StateModel result = stateUseCase.getStateById(stateId);

        assertNotNull(result);
    }

}

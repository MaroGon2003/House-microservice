package com.powerup.house_microservice.domain;

import com.powerup.house_microservice.domain.exception.StateAlreadyExistException;
import com.powerup.house_microservice.domain.exception.StateNotFoundException;
import com.powerup.house_microservice.domain.factory.StateTestDataFactory;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.IStatePersistencePort;
import com.powerup.house_microservice.domain.usecase.StateUseCase;
import com.powerup.house_microservice.domain.utils.ErrorMessages;
import com.powerup.house_microservice.domain.utils.ValidationConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void createState_ShouldThrowException_WhenNameIsBlank() {
        StateModel state = StateTestDataFactory.createStateModel();
        state.setName("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stateUseCase.create(state));

        assertEquals(ErrorMessages.NAME_CANNOT_BE_BLANK, exception.getMessage());
    }

    @Test
    void createState_ShouldThrowException_WhenDescriptionIsBlank() {
        StateModel state = StateTestDataFactory.createStateModel();
        state.setDescription("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stateUseCase.create(state));

        assertEquals(ErrorMessages.DESCRIPTION_CANNOT_BE_BLANK, exception.getMessage());
    }

    @Test
    void createState_ShouldThrowException_WhenNameIsTooShort() {
        StateModel state = StateTestDataFactory.createStateModel();
        state.setName("a"); // Assuming NAME_MIN_LENGTH is greater than 1

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stateUseCase.create(state));

        assertEquals(ErrorMessages.NAME_LENGTH_ERROR, exception.getMessage());
    }

    @Test
    void createState_ShouldThrowException_WhenNameIsTooLong() {
        StateModel state = StateTestDataFactory.createStateModel();
        state.setName("a".repeat(ValidationConstants.NAME_MAX_LENGTH + 1));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stateUseCase.create(state));

        assertEquals(ErrorMessages.NAME_LENGTH_ERROR, exception.getMessage());
    }

    @Test
    void createState_ShouldThrowException_WhenDescriptionIsTooShort() {
        StateModel state = StateTestDataFactory.createStateModel();
        state.setDescription("a"); // Assuming DESCRIPTION_MIN_LENGTH is greater than 1

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stateUseCase.create(state));

        assertEquals(ErrorMessages.DESCRIPTION_LENGTH_ERROR_STATE_CITY, exception.getMessage());
    }

    @Test
    void createState_ShouldThrowException_WhenDescriptionIsTooLong() {
        StateModel state = StateTestDataFactory.createStateModel();
        state.setDescription("a".repeat(ValidationConstants.DESCRIPTION_MAX_LENGTH_STATE_CITY + 1));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stateUseCase.create(state));

        assertEquals(ErrorMessages.DESCRIPTION_LENGTH_ERROR_STATE_CITY, exception.getMessage());
    }

}

package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.IStatePersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IStateEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IStateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class StateJpaAdapter implements IStatePersistencePort {

    private final IStateRepository stateRepository;
    private final IStateEntityMapper stateEntityMapper;

    @Override
    public void create(StateModel state) {

        stateRepository.save(stateEntityMapper.toStateEntity(state));

    }

    @Override
    public boolean existStateByName(String name) {
        return stateRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean existStateById(Long id) {
        return stateRepository.existsById(id);
    }

    @Override
    public StateModel getStateById(Long id) {
        return stateEntityMapper.toStateModel(stateRepository.findById(id).orElse(null));
    }

}

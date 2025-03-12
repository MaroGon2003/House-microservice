package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.ILocationEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ICityRepository;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ILocationRepository;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IStateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class LocationJpaAdapter implements ILocationPersistencePort {

    private final ILocationRepository locationRepository;
    private final IStateRepository stateRepository;
    private final ICityRepository cityRepository;
    private final ILocationEntityMapper locationEntityMapper;


    @Override
    public void saveState(StateModel state) {
        stateRepository.save(locationEntityMapper.toStateEntity(state));
    }

    @Override
    public CityModel saveCity(CityModel city) {
        return locationEntityMapper.toCityModel(cityRepository.save(locationEntityMapper.toCityEntity(city)));
    }

    @Override
    public boolean existStateById(Long stateId) {
        return stateRepository.existsById(stateId);
    }

    @Override
    public StateModel getStateById(Long stateId) {
        return locationEntityMapper.toStateModel(stateRepository.findById(stateId).orElseThrow());
    }

    @Override
    public void saveLocation(LocationModel location) {
        locationRepository.save(locationEntityMapper.toLocationEntity(location));
    }

    @Override
    public boolean existStateByName(String stateName) {
        return stateRepository.existsByNameIgnoreCase(stateName);
    }

    @Override
    public boolean existStateAndCity(String cityName, Long stateId) {
        return locationRepository.existsByCityNameIgnoreCaseAndStateId(cityName, stateId);
    }
}

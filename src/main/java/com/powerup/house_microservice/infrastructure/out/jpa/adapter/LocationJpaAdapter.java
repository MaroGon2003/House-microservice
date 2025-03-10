package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.ILocationEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ILocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class LocationJpaAdapter implements ILocationPersistencePort {

    private final ILocationRepository locationRepository;
    private final ILocationEntityMapper locationEntityMapper;

    @Override
    public void saveLocation(LocationModel location) {

        locationRepository.save(locationEntityMapper.toLocationEntity(location));

    }

    @Override
    public boolean existSLocationByStateAndCity(String state, String city) {
        return locationRepository.existsByStateAndCity(state, city);
    }
}

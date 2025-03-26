package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.spi.ICityPersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.ICityEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ICityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class CityJpaAdapter implements ICityPersistencePort {

    private final ICityRepository cityRepository;
    private final ICityEntityMapper cityEntityMapper;
    @Override
    public void create(CityModel city) {

        cityRepository.save(cityEntityMapper.toCityEntity(city));

    }

    @Override
    public CityModel getCityById(Long id) {
        return cityEntityMapper.toCityModel(cityRepository.findById(id).orElse(null));
    }
}

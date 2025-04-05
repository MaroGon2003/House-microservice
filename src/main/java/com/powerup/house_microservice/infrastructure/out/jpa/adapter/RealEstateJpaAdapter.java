package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.RealEstateModel;
import com.powerup.house_microservice.domain.spi.IRealEstatePersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IRealEstateEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IRealEstateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class RealEstateJpaAdapter implements IRealEstatePersistencePort {

    private final IRealEstateRepository realEstateRepository;
    private final IRealEstateEntityMapper realEstateEntityMapper;

    @Override
    public void createRealEstate(RealEstateModel realEstateModel) {

        realEstateRepository.save(realEstateEntityMapper.toRealEstateEntity(realEstateModel));

    }

}

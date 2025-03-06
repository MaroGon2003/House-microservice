package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IRealEstateCategoryEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IRealEstateCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class RealEstateCategoryJpaAdapter implements IRealEstateCategoryPersistencePort {

    private final IRealEstateCategoryRepository realEstateCategoryRepository;
    private final IRealEstateCategoryEntityMapper realEstateCategoryEntityMapper;

    @Override
    public void saveRealEstateCategory(RealEstateCategoryModel realEstateCategory) {

        realEstateCategoryRepository.save(realEstateCategoryEntityMapper.toRealEstateCategoryEntity(realEstateCategory));

    }

    @Override
    public boolean existsRealEstateCategoryByName(String name) {
        return realEstateCategoryRepository.existsByName(name);
    }
}

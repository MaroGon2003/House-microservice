package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IRealEstateCategoryEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IRealEstateCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    public List<RealEstateCategoryModel> getAllRealEstateCategories(int pageNumber, int pageSize) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return realEstateCategoryEntityMapper.toRealEstateCategoryModelList(realEstateCategoryRepository.findAll(pageable).getContent());
    }

    @Override
    public boolean existsRealEstateCategoryByName(String name) {
        return realEstateCategoryRepository.existsByName(name);
    }
}

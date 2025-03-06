package com.powerup.house_microservice.infrastructure.out.jpa.repository;

import com.powerup.house_microservice.infrastructure.out.jpa.entity.RealEstateCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRealEstateCategoryRepository extends JpaRepository<RealEstateCategoryEntity, Long> {
    boolean existsByName(String name);
}

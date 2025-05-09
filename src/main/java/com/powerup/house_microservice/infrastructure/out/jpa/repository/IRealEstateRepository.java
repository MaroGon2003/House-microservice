package com.powerup.house_microservice.infrastructure.out.jpa.repository;

import com.powerup.house_microservice.infrastructure.out.jpa.entity.RealEstateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRealEstateRepository extends JpaRepository<RealEstateEntity, Long>, JpaSpecificationExecutor<RealEstateEntity> {
}

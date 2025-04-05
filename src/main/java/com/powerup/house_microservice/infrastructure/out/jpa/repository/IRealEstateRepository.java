package com.powerup.house_microservice.infrastructure.out.jpa.repository;

import com.powerup.house_microservice.infrastructure.out.jpa.entity.RealEstateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRealEstateRepository extends JpaRepository<RealEstateEntity, Long> {
}

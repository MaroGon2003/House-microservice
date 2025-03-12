package com.powerup.house_microservice.infrastructure.out.jpa.repository;

import com.powerup.house_microservice.infrastructure.out.jpa.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository extends JpaRepository<LocationEntity, LocationEntity.LocationId> {

    boolean existsByCityNameIgnoreCaseAndStateId(String cityName, Long stateId);

}

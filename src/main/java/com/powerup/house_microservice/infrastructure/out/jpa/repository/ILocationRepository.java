package com.powerup.house_microservice.infrastructure.out.jpa.repository;

import com.powerup.house_microservice.infrastructure.out.jpa.entity.LocationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILocationRepository extends JpaRepository<LocationEntity, LocationEntity.LocationId> {

    boolean existsByCityNameIgnoreCaseAndStateId(String cityName, Long stateId);
    List<LocationEntity> findAllByCityNameIgnoreCase(String cityName, Pageable pageable);
    List<LocationEntity> findAllByStateNameIgnoreCase(String stateName, Pageable pageable);

}

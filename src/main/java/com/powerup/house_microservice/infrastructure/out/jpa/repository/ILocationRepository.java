package com.powerup.house_microservice.infrastructure.out.jpa.repository;

import com.powerup.house_microservice.infrastructure.out.jpa.entity.LocationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILocationRepository extends JpaRepository<LocationEntity, Long> {

    boolean existsByCityNameIgnoreCaseAndCityStateId(String cityName, Long stateId);
    List<LocationEntity> findAllByCityNameIgnoreCase(String cityName, Pageable pageable);
    List<LocationEntity> findAllByCityStateNameIgnoreCase(String stateName, Pageable pageable);

    List<LocationEntity> findAllByCityStateNameIgnoreCaseAndCityNameIgnoreCase(String stateName, String cityName, Pageable pageable);

}

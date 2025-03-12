package com.powerup.house_microservice.infrastructure.out.jpa.repository;

import com.powerup.house_microservice.infrastructure.out.jpa.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStateRepository extends JpaRepository<StateEntity, Long> {

    boolean existsByNameIgnoreCase(String name);

}

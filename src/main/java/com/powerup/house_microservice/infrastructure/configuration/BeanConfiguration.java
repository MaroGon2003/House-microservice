package com.powerup.house_microservice.infrastructure.configuration;

import com.powerup.house_microservice.domain.api.ILocatoinServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.usecase.LocationUseCase;
import com.powerup.house_microservice.domain.usecase.RealEstateCategoryUseCase;
import com.powerup.house_microservice.infrastructure.out.jpa.adapter.LocationJpaAdapter;
import com.powerup.house_microservice.infrastructure.out.jpa.adapter.RealEstateCategoryJpaAdapter;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.ILocationEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IRealEstateCategoryEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ILocationRepository;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IRealEstateCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRealEstateCategoryRepository realEstateCategoryRepository;
    private final IRealEstateCategoryEntityMapper realEstateCategoryEntityMapper;
    private final ILocationRepository locationRepository;
    private final ILocationEntityMapper locationEntityMapper;

    //persistencePort
    @Bean
    public IRealEstateCategoryPersistencePort realEstateCategoryPersistencePort() {
        return new RealEstateCategoryJpaAdapter(realEstateCategoryRepository,realEstateCategoryEntityMapper);
    }

    @Bean
    public IRealEstateCategoryServicePort realEstateCategoryServicePort() {
        return new RealEstateCategoryUseCase(realEstateCategoryPersistencePort());
    }

    @Bean
    public ILocationPersistencePort locationPersistencePort() {
        return new LocationJpaAdapter(locationRepository, locationEntityMapper);
    }

    @Bean
    public ILocatoinServicePort locationServicePort() {
        return new LocationUseCase(locationPersistencePort());
    }

}

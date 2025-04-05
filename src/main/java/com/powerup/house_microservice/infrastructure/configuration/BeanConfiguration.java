package com.powerup.house_microservice.infrastructure.configuration;

import com.powerup.house_microservice.domain.api.*;
import com.powerup.house_microservice.domain.spi.*;
import com.powerup.house_microservice.domain.usecase.*;
import com.powerup.house_microservice.infrastructure.out.jpa.adapter.*;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.*;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.*;
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
    private final IStateRepository stateRepository;
    private final ICityRepository cityRepository;
    private final ICityEntityMapper cityEntityMapper;
    private final IStateEntityMapper stateEntityMapper;
    private final IRealEstateEntityMapper realEstateEntityMapper;
    private final IRealEstateRepository realEstateRepository;

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
    public StateUseCase stateUseCase() {
        return new StateUseCase(statePersistencePort());
    }
    @Bean
    public ILocationServicePort locationServicePort() {
        return new LocationUseCase(locationPersistencePort(), cityServicePort());
    }

    @Bean
    public ICityPersistencePort cityPersistencePort() {
        return new CityJpaAdapter(cityRepository, cityEntityMapper);
    }

    @Bean
    public IStatePersistencePort statePersistencePort() {
        return new StateJpaAdapter(stateRepository, stateEntityMapper);
    }

    @Bean
    public ICityServicePort cityServicePort() {
        return new CityUseCase(cityPersistencePort(), stateUseCase());
    }

    @Bean
    public IStateServicePort stateServicePort() {
        return new StateUseCase(statePersistencePort());
    }

    @Bean
    public IRealEstatePersistencePort realEstatePersistencePort() {
        return new RealEstateJpaAdapter(realEstateRepository, realEstateEntityMapper);
    }

    @Bean
    public RealEstateCategoryUseCase realEstateCategoryUseCase() {
        return new RealEstateCategoryUseCase(realEstateCategoryPersistencePort());
    }

    @Bean
    public IRealEstateServicePort realEstateServicePort() {
        return new RealEstateUseCase(realEstatePersistencePort(), locationServicePort(), realEstateCategoryUseCase());
    }

}

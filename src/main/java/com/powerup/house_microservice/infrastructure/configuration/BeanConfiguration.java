package com.powerup.house_microservice.infrastructure.configuration;

import com.powerup.house_microservice.domain.api.ICityServicePort;
import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.api.IStateServicePort;
import com.powerup.house_microservice.domain.spi.ICityPersistencePort;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.spi.IRealEstateCategoryPersistencePort;
import com.powerup.house_microservice.domain.spi.IStatePersistencePort;
import com.powerup.house_microservice.domain.usecase.CityUseCase;
import com.powerup.house_microservice.domain.usecase.LocationUseCase;
import com.powerup.house_microservice.domain.usecase.RealEstateCategoryUseCase;
import com.powerup.house_microservice.domain.usecase.StateUseCase;
import com.powerup.house_microservice.infrastructure.out.jpa.adapter.CityJpaAdapter;
import com.powerup.house_microservice.infrastructure.out.jpa.adapter.LocationJpaAdapter;
import com.powerup.house_microservice.infrastructure.out.jpa.adapter.RealEstateCategoryJpaAdapter;
import com.powerup.house_microservice.infrastructure.out.jpa.adapter.StateJpaAdapter;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.ICityEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.ILocationEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IRealEstateCategoryEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IStateEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ICityRepository;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ILocationRepository;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IRealEstateCategoryRepository;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IStateRepository;
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
    public CityUseCase cityUseCase() {
        return new CityUseCase(cityPersistencePort(), stateUseCase());
    }

    @Bean
    public ILocationServicePort locationServicePort() {
        return new LocationUseCase(locationPersistencePort(), cityUseCase());
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

}

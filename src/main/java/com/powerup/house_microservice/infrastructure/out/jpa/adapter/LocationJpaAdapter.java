package com.powerup.house_microservice.infrastructure.out.jpa.adapter;


import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.ILocationEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.ILocationRepository;
import com.powerup.house_microservice.infrastructure.utils.InfrastructureConstants;
import com.powerup.house_microservice.infrastructure.utils.PaginationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public class LocationJpaAdapter implements ILocationPersistencePort {

    private final ILocationRepository locationRepository;
    private final ILocationEntityMapper locationEntityMapper;

    @Override
    public void saveLocation(LocationModel location) {
        locationRepository.save(locationEntityMapper.toLocationEntity(location));
    }

    @Override
    public boolean existStateAndCity(String cityName, Long stateId) {
        return locationRepository.existsByCityNameIgnoreCaseAndCityStateId(cityName, stateId);
    }

    @Override
    public List<LocationModel> getAllLocationsByCityName(String cityName, int page, int size,  String sortDirection) {
        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_CITY_NAME, sortDirection);
        return locationEntityMapper.toLocationModelList(locationRepository.findAllByCityNameIgnoreCase(cityName, pageable));
    }

    @Override
    public List<LocationModel> getAllLocationsByStateName(String stateName, int page, int size,  String sortDirection) {
        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_CITY_NAME, sortDirection);
        return locationEntityMapper.toLocationModelList(locationRepository.findAllByCityStateNameIgnoreCase(stateName, pageable));
    }

    @Override
    public List<LocationModel> getAllLocationsByStateAndCityName(String stateName, String cityName, int page, int size, String sortDirection) {
        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_CITY_NAME, sortDirection);
        return locationEntityMapper.toLocationModelList(locationRepository.findAllByCityStateNameIgnoreCaseAndCityNameIgnoreCase(stateName, cityName, pageable));
    }

    @Override
    public List<LocationModel> getAllLocations(int page, int size, String sortDirection) {
        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_CITY_NAME, sortDirection);
        return locationEntityMapper.toLocationModelList(locationRepository.findAll(pageable).getContent());
    }

    @Override
    public LocationModel getLocationById(Long locationId) {
        return locationEntityMapper.toLocationModel(locationRepository.findById(locationId).orElse(null));
    }
}

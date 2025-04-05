package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ICityServicePort;
import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.exception.CityNotFoundException;
import com.powerup.house_microservice.domain.exception.LocationNotFoundException;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.spi.ILocationPersistencePort;
import com.powerup.house_microservice.domain.utils.DomainConstants;
import com.powerup.house_microservice.domain.utils.PaginationValidator;

import java.util.List;
import java.util.Optional;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;
    private final ICityServicePort cityServicePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort, ICityServicePort cityServicePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.cityServicePort = cityServicePort;
    }

    @Override
    public List<LocationModel> getLocations(String stateName, String cityName, int page, int size, boolean ascending) {

        String sortDirection = ascending ? DomainConstants.ASC : DomainConstants.DESC;

        PaginationValidator.validatePaginationParameters(page, size, sortDirection);

        List<LocationModel> locationModelList;

        if (stateName != null && !stateName.isBlank() && cityName != null && !cityName.isBlank()) {
            locationModelList = locationPersistencePort.getAllLocationsByStateAndCityName(stateName, cityName, page, size, sortDirection);
        } else if (stateName != null && !stateName.isBlank()) {
            locationModelList = locationPersistencePort.getAllLocationsByStateName(stateName, page, size, sortDirection);
        } else if (cityName != null && !cityName.isBlank()) {
            locationModelList = locationPersistencePort.getAllLocationsByCityName(cityName, page, size, sortDirection);
        } else {
            locationModelList = locationPersistencePort.getAllLocations(page, size, sortDirection);
        }

        return locationModelList;

    }

    @Override
    public void saveLocation(Long cityId, String neighborhood) {

        CityModel city = Optional.ofNullable(cityServicePort.getCityById(cityId))
                .orElseThrow(() -> new CityNotFoundException(DomainConstants.CITY_NOT_FOUND,cityId));

        LocationModel location = new LocationModel();
        location.setCity(city);
        location.setNeighborhood(neighborhood);

        locationPersistencePort.saveLocation(location);
    }

    @Override
    public LocationModel getLocationById(Long locationId) {

        Optional<LocationModel> location = Optional.ofNullable(locationPersistencePort.getLocationById(locationId));

        if(location.isEmpty()) {
            throw new LocationNotFoundException(DomainConstants.LOCATION_NOT_FOUND, locationId);
        }

        return location.get();
    }
}

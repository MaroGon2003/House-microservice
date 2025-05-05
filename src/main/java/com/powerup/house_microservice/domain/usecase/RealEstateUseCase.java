package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateServicePort;
import com.powerup.house_microservice.domain.exception.PublicationDateException;
import com.powerup.house_microservice.domain.model.*;
import com.powerup.house_microservice.domain.spi.IRealEstatePersistencePort;
import com.powerup.house_microservice.domain.utils.DomainConstants;
import com.powerup.house_microservice.domain.utils.PaginationValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RealEstateUseCase implements IRealEstateServicePort {

    private final IRealEstatePersistencePort realEstatePersistencePort;
    private final ILocationServicePort locationServicePort;
    private final IRealEstateCategoryServicePort realEstateCategoryServicePort;

    public RealEstateUseCase(IRealEstatePersistencePort realEstatePersistencePort, ILocationServicePort locationServicePort, IRealEstateCategoryServicePort realEstateCategoryServicePort) {
        this.realEstatePersistencePort = realEstatePersistencePort;
        this.locationServicePort = locationServicePort;
        this.realEstateCategoryServicePort = realEstateCategoryServicePort;
    }

    @Override
    public void createRealEstate(RealEstateModel realEstateModel) {

        LocalDate today = LocalDate.now();

        if (realEstateModel.getActiveFrom().isBefore(today)) {
            throw new PublicationDateException(DomainConstants.PUBLICATION_DATE_NOT_IN_PAST);
        }

        if (realEstateModel.getActiveFrom().isAfter(today.plusMonths(DomainConstants.MAX_MONTHS))) {
            throw new PublicationDateException(DomainConstants.PUBLICATION_DATE_NOT_MORE_THAN_1_MONTH);
        }

        LocationModel location = locationServicePort.getLocationById(realEstateModel.getLocation().getId());
        realEstateModel.setLocation(location);

        RealEstateCategoryModel realEstateCategory = realEstateCategoryServicePort.getRealEstateCategoryById(realEstateModel.getCategory().getId());
        realEstateModel.setCategory(realEstateCategory);

        realEstateModel.setCreatedOn(today );

        if (realEstateModel.getActiveFrom().isEqual(today)) {
            realEstateModel.setStatus(ListingStatus.PUBLISHED);
        } else {
            realEstateModel.setStatus(ListingStatus.LISTING_PAUSED);
        }

        realEstatePersistencePort.createRealEstate(realEstateModel);

    }


    @Override
    public List<RealEstateModel> getRealEstates(String stateName, String cityName, Long categoryId, Integer rooms, Integer bathrooms, BigDecimal minPrice, BigDecimal maxPrice, int page, int size, boolean ascending) {

        String sortDirection = ascending ? DomainConstants.ASC : DomainConstants.DESC;

        PaginationValidator.validatePaginationParameters(page, size, sortDirection);

        RealEstateFilter filter = new RealEstateFilter();
        filter.setStateName(stateName);
        filter.setCityName(cityName);
        filter.setCategoryId(categoryId);
        filter.setRooms(rooms);
        filter.setBathrooms(bathrooms);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        filter.setPage(page);
        filter.setSize(size);
        filter.setSortDirection(sortDirection);

        return realEstatePersistencePort.getRealEstatesByFilters(filter);

    }

}

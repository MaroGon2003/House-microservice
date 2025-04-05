package com.powerup.house_microservice.domain.usecase;

import com.powerup.house_microservice.domain.api.ILocationServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateCategoryServicePort;
import com.powerup.house_microservice.domain.api.IRealEstateServicePort;
import com.powerup.house_microservice.domain.exception.PublicationDateException;
import com.powerup.house_microservice.domain.model.ListingStatus;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.domain.model.RealEstateModel;
import com.powerup.house_microservice.domain.spi.IRealEstatePersistencePort;
import com.powerup.house_microservice.domain.utils.DomainConstants;

import java.time.LocalDate;

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

}

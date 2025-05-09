package com.powerup.house_microservice.infrastructure.out.jpa.adapter;

import com.powerup.house_microservice.domain.model.ListingStatus;
import com.powerup.house_microservice.domain.model.RealEstateFilter;
import com.powerup.house_microservice.domain.model.RealEstateModel;
import com.powerup.house_microservice.domain.spi.IRealEstatePersistencePort;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.RealEstateEntity;
import com.powerup.house_microservice.infrastructure.out.jpa.mapper.IRealEstateEntityMapper;
import com.powerup.house_microservice.infrastructure.out.jpa.repository.IRealEstateRepository;
import com.powerup.house_microservice.infrastructure.utils.InfrastructureConstants;
import com.powerup.house_microservice.infrastructure.utils.PaginationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public class RealEstateJpaAdapter implements IRealEstatePersistencePort {

    private final IRealEstateRepository realEstateRepository;
    private final IRealEstateEntityMapper realEstateEntityMapper;

    @Override
    public void createRealEstate(RealEstateModel realEstateModel) {

        realEstateRepository.save(realEstateEntityMapper.toRealEstateEntity(realEstateModel));

    }

    @Override
    public List<RealEstateModel> getRealEstatesByFilters(RealEstateFilter filter) {
        Pageable pageable = PaginationUtils.createPageable(
                filter.getPage(),
                filter.getSize(),
                InfrastructureConstants.FIELD_PRICE,
                filter.getSortDirection()
        );

        Specification<RealEstateEntity> spec = Specification.where((root, query, cb) ->
                cb.equal(root.get(InfrastructureConstants.FIELD_STATUS), ListingStatus.PUBLISHED)
        );

        if (filter.getStateName() != null && !filter.getStateName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.join(InfrastructureConstants.FIELD_LOCATION)
                                    .join(InfrastructureConstants.FIELD_CITY)
                                    .join(InfrastructureConstants.FIELD_STATE)
                                    .get(InfrastructureConstants.FIELD_NAME),
                            filter.getStateName())
            );
        }

        if (filter.getCityName() != null && !filter.getCityName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.join(InfrastructureConstants.FIELD_LOCATION)
                                    .join(InfrastructureConstants.FIELD_CITY)
                                    .get(InfrastructureConstants.FIELD_NAME),
                            filter.getCityName())
            );
        }

        if (filter.getCategoryId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.join(InfrastructureConstants.FIELD_CATEGORY).get(InfrastructureConstants.FIELD_ID), filter.getCategoryId())
            );
        }

        if (filter.getRooms() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get(InfrastructureConstants.FIELD_ROOMS_COUNT), filter.getRooms())
            );
        }

        if (filter.getBathrooms() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get(InfrastructureConstants.FIELD_BATHROOMS_COUNT), filter.getBathrooms())
            );
        }

        if (filter.getMinPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get(InfrastructureConstants.FIELD_PRICE), filter.getMinPrice())
            );
        }

        if (filter.getMaxPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get(InfrastructureConstants.FIELD_PRICE), filter.getMaxPrice())
            );
        }

        Page<RealEstateEntity> pageResult = realEstateRepository.findAll(spec, pageable);

        return realEstateEntityMapper.toRealEstateModelList(pageResult.getContent());
    }


}

package com.powerup.house_microservice.infrastructure.out.jpa.mapper;

import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILocationEntityMapper {

    LocationEntity toLocationEntity(LocationModel locationModel);

}

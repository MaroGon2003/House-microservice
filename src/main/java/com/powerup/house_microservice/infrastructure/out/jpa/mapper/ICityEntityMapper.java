package com.powerup.house_microservice.infrastructure.out.jpa.mapper;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICityEntityMapper {

    CityEntity toCityEntity(CityModel cityModel);

    CityModel toCityModel(CityEntity cityEntity);

}

package com.powerup.house_microservice.infrastructure.out.jpa.mapper;

import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.CityEntity;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.LocationEntity;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILocationEntityMapper {

    @Mapping(target = "city", source = "city")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "locationId" , expression = "java(new LocationEntity.LocationId(locationModel.getCity().getId(), locationModel.getState().getId()))")
    LocationEntity toLocationEntity(LocationModel locationModel);

    StateEntity toStateEntity(StateModel stateModel);

    CityEntity toCityEntity(CityModel cityModel);

    CityModel toCityModel(CityEntity cityEntity);

    StateModel toStateModel(StateEntity stateEntity);

    List<LocationModel> toLocationModelList(List<LocationEntity> locationEntities);
}

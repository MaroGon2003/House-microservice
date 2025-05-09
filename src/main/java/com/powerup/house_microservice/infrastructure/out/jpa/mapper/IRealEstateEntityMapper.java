package com.powerup.house_microservice.infrastructure.out.jpa.mapper;

import com.powerup.house_microservice.domain.model.RealEstateModel;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.RealEstateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRealEstateEntityMapper {

    RealEstateEntity toRealEstateEntity(RealEstateModel realEstateModel);

    RealEstateModel toRealEstateModel(RealEstateEntity realEstateEntity);
    List<RealEstateModel> toRealEstateModelList(List<RealEstateEntity> realEstateEntities);

}

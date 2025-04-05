package com.powerup.house_microservice.infrastructure.out.jpa.mapper;

import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.RealEstateCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRealEstateCategoryEntityMapper {

    RealEstateCategoryEntity toRealEstateCategoryEntity(RealEstateCategoryModel realEstateCategoryModel);
    List<RealEstateCategoryModel> toRealEstateCategoryModelList(List<RealEstateCategoryEntity> realEstateCategoryEntityList);
    RealEstateCategoryModel toRealEstateCategoryModel(RealEstateCategoryEntity realEstateCategoryEntity);

}

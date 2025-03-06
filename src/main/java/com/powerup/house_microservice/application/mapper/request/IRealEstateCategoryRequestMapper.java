package com.powerup.house_microservice.application.mapper.request;

import com.powerup.house_microservice.application.dto.request.RealEstateCategoryDto;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRealEstateCategoryRequestMapper {

    RealEstateCategoryModel toRealEstateCategoryModel(RealEstateCategoryDto realEstateCategoryDto);

}

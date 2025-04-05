package com.powerup.house_microservice.application.mapper.request;

import com.powerup.house_microservice.application.dto.request.RealEstateRequestDto;
import com.powerup.house_microservice.domain.model.RealEstateModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRealEstateRequestMapper {

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "location.id", source = "locationId")
    RealEstateModel toRealEstateModel(RealEstateRequestDto realEstateRequestDto);

}

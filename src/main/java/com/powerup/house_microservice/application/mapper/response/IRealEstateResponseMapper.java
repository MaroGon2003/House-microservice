package com.powerup.house_microservice.application.mapper.response;

import com.powerup.house_microservice.application.dto.response.RealEstateResponseDto;
import com.powerup.house_microservice.domain.model.RealEstateModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRealEstateResponseMapper {

    @Mapping(source = "location.city.state", target = "location.state")
    RealEstateResponseDto toRealEstateResponseDto(RealEstateModel realEstateModel);

    List<RealEstateResponseDto> toRealEstateResponseDtoList(List<RealEstateModel> realEstateModelList);

}

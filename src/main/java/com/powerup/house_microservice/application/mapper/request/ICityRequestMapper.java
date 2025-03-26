package com.powerup.house_microservice.application.mapper.request;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.domain.model.CityModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICityRequestMapper {

    CityModel toCityModel(CityRequestDto cityRequestDto);


}

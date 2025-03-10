package com.powerup.house_microservice.application.mapper.request;

import com.powerup.house_microservice.application.dto.request.LocationRequestDto;
import com.powerup.house_microservice.domain.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILocationRequestMapper {

    LocationModel toLocationModel(LocationRequestDto locationRequestDto);

}

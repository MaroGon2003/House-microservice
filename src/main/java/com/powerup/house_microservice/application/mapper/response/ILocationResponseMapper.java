package com.powerup.house_microservice.application.mapper.response;

import com.powerup.house_microservice.application.dto.response.CityResponseDto;
import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.dto.response.StateResponseDto;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.LocationModel;
import com.powerup.house_microservice.domain.model.StateModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILocationResponseMapper {

    @Mapping(source = "state", target = "state")
    @Mapping(source = "city", target = "city")
    LocationResponseDto locationModelToLocationResponseDto(LocationModel locationModel);

    List<LocationResponseDto> toLocationResponseDtoList(List<LocationModel> locationModelList);

    StateResponseDto stateModelToStateResponseDto(StateModel stateModel);

    CityResponseDto cityModelToCityResponseDto(CityModel cityModel);

    default List<CityResponseDto> cityModelToCityResponseDtoList(CityModel cityModel) {
        return List.of(cityModelToCityResponseDto(cityModel));
    }

}

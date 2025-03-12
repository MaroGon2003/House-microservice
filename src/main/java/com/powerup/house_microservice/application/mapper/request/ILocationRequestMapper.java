package com.powerup.house_microservice.application.mapper.request;

import com.powerup.house_microservice.application.dto.request.CityRequestDto;
import com.powerup.house_microservice.application.dto.request.StateRequestDto;
import com.powerup.house_microservice.domain.model.CityModel;
import com.powerup.house_microservice.domain.model.StateModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILocationRequestMapper {

    StateModel toStateModel(StateRequestDto stateRequestDto);

    CityModel toCityModel(CityRequestDto cityRequestDto);

}

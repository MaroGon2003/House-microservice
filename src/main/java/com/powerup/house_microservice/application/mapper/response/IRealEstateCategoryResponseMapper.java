package com.powerup.house_microservice.application.mapper.response;

import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;
import com.powerup.house_microservice.domain.model.RealEstateCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRealEstateCategoryResponseMapper {

    List<RealEstateCategoryResponseDto> toRealEstateCategoryResponseDtoList(List<RealEstateCategoryModel> realEstateCategoryModelList);

}

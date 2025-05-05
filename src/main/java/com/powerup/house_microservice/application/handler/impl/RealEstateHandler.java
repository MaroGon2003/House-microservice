package com.powerup.house_microservice.application.handler.impl;

import com.powerup.house_microservice.application.dto.request.RealEstateRequestDto;
import com.powerup.house_microservice.application.dto.response.RealEstateResponseDto;
import com.powerup.house_microservice.application.handler.IRealEstateHandler;
import com.powerup.house_microservice.application.mapper.request.IRealEstateRequestMapper;
import com.powerup.house_microservice.application.mapper.response.IRealEstateResponseMapper;
import com.powerup.house_microservice.application.utils.PagedResult;
import com.powerup.house_microservice.domain.api.IRealEstateServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateHandler implements IRealEstateHandler {

    private final IRealEstateServicePort realEstateServicePort;
    private final IRealEstateRequestMapper realEstateRequestMapper;
    private final IRealEstateResponseMapper realEstateResponseMapper;
    @Override
    public void createRealEstate(RealEstateRequestDto realEstateRequestDto) {

        realEstateServicePort.createRealEstate(realEstateRequestMapper.toRealEstateModel(realEstateRequestDto));

    }

    @Override
    public PagedResult<RealEstateResponseDto> getRealEstates(String stateName, String cityName, Long categoryId, Integer rooms, Integer bathrooms, BigDecimal minPrice, BigDecimal maxPrice, int page, int size, boolean ascending) {
        List<RealEstateResponseDto> realEstateResponseDto = realEstateResponseMapper.toRealEstateResponseDtoList(
                realEstateServicePort.getRealEstates(stateName, cityName, categoryId, rooms, bathrooms, minPrice, maxPrice, page, size, ascending)
        );

        return new PagedResult<>(realEstateResponseDto, page, size);
    }

}

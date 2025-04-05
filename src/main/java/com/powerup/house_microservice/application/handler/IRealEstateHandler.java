package com.powerup.house_microservice.application.handler;

import com.powerup.house_microservice.application.dto.request.RealEstateRequestDto;

public interface IRealEstateHandler {

    void createRealEstate(RealEstateRequestDto realEstateRequestDto);

}

package com.powerup.house_microservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponseDto {

    private long id;
    private StateResponseDto state;
    private CityResponseDto city;
    private String neighborhood;

}

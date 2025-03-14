package com.powerup.house_microservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponseDto {


    StateResponseDto state;
    List<CityResponseDto> city;


}

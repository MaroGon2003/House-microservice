package com.powerup.house_microservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequestDto {

    private Long cityId;
    private String neighborhood;

}

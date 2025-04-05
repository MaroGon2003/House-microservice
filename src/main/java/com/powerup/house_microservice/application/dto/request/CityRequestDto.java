package com.powerup.house_microservice.application.dto.request;


import com.powerup.house_microservice.application.utils.ApplicationConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityRequestDto {

    @NotBlank
    @Size(min = ApplicationConstants.NAME_MIN_SIZE_CITY_STATE, max = ApplicationConstants.NAME_MAX_SIZE_CITY_STATE, message = ApplicationConstants.NAME_SIZE_LOCATION)
    private String name;

    @NotBlank
    @Size(min = ApplicationConstants.DESCRIPTION_MIN_SIZE_CITY_STATE, max = ApplicationConstants.DESCRIPTION_MAX_SIZE_CITY_STATE, message = ApplicationConstants.DESCRIPTION_SIZE_LOCATION)
  
    private String description;

}

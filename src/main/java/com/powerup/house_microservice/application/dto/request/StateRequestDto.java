package com.powerup.house_microservice.application.dto.request;


import com.powerup.house_microservice.application.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateRequestDto {

    @NotBlank
    @Size(min = 2, max = 50, message = ValidationMessages.NAME_SIZE_LOCATION)
    private String name;

    @NotBlank
    @Size(min = 2, max = 120, message = ValidationMessages.DESCRIPTION_SIZE_LOCATION)

    private String description;

}

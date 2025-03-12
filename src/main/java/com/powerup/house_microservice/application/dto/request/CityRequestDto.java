package com.powerup.house_microservice.application.dto.request;

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
    @Size(min = 2, max = 50, message = "La ciudad debe tener entre 2 y 50 caracteres")
    private String name;

    @NotBlank
    @Size(min = 2, max = 120, message = "La descripción debe tener entre 2 y 120 caracteres")
    private String description;

}

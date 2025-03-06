package com.powerup.house_microservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateCategoryResponseDto {
    private Long id;
    private String name;
    private String description;

}

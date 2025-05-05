package com.powerup.house_microservice.application.dto.response;

import com.powerup.house_microservice.domain.model.ListingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateResponseDto {

    private Long id;
    private String name;
    private String description;
    private RealEstateCategoryResponseDto category;
    private int roomsCount;
    private int bathroomsCount;
    private BigDecimal price;
    private LocationResponseDto location;
    private LocalDate activeFrom;
    private ListingStatus status;

}

package com.powerup.house_microservice.application.dto.request;

import com.powerup.house_microservice.application.utils.ApplicationConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateRequestDto {

    @NotBlank(message = ApplicationConstants.NAME_REQUIRED_MESSAGE)
    @Size(max = ApplicationConstants.NAME_MAX_SIZE_REAL_ESTATE_REQUEST, message = ApplicationConstants.NAME_SIZE_REAL_ESTATE_REQUEST_MESSAGE)
    private String name;

    @NotBlank(message = ApplicationConstants.DESCRIPTION_REQUIRED_MESSAGE)
    @Size(max = ApplicationConstants.DESCRIPTION_MAX_SIZE_REAL_ESTATE_REQUEST, message = ApplicationConstants.DESCRIPTION_SIZE_REAL_ESTATE_REQUEST_MESSAGE)
    private String description;

    @NotNull(message = ApplicationConstants.CATEGORY_ID_REQUIRED_MESSAGE)
    private Long categoryId;

    @Min(value = ApplicationConstants.MIN_ROOMS_COUNT, message = ApplicationConstants.ROOMS_COUNT_MIN_MESSAGE)
    private int roomsCount;

    @Min(value = ApplicationConstants.MIN_BATHROOMS_COUNT, message = ApplicationConstants.BATHROOMS_COUNT_MIN_MESSAGE)
    private int bathroomsCount;

    @NotNull(message = ApplicationConstants.PRICE_REQUIRED_MESSAGE)
    @DecimalMin(value = ApplicationConstants.PRICE_MIN_VALUE, message = ApplicationConstants.PRICE_MIN_MESSAGE)
    private BigDecimal price;

    @NotNull(message = ApplicationConstants.LOCATION_ID_REQUIRED_MESSAGE)
    private Long locationId;

    @NotNull(message = ApplicationConstants.ACTIVE_FROM_REQUIRED_MESSAGE)
    private LocalDate activeFrom;

}

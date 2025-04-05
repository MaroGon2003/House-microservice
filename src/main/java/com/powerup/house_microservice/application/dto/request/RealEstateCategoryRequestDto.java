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
public class RealEstateCategoryRequestDto {

    @NotBlank
    @Size(min = ApplicationConstants.NAME_MIN_SIZE_REAL_ESTATE_CATEGORY, max = ApplicationConstants.NAME_MAX_SIZE_REAL_ESTATE_CATEGORY, message = ApplicationConstants.NAME_SIZE_REAL_ESTATE_CATEGORY)
    private String name;

    @NotBlank
    @Size(max = ApplicationConstants.DESCRIPTION_MAX_SIZE_REAL_ESTATE_CATEGORY, message =ApplicationConstants.DESCRIPTION_SIZE_REAL_ESTATE_CATEGORY)
    private String description;

}

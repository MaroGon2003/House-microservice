package com.powerup.house_microservice.domain.utils;

public class StateCityValidationUtils {

    private StateCityValidationUtils() {}

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.NAME_CANNOT_BE_BLANK);
        }
        if (name.length() < ValidationConstants.NAME_MIN_LENGTH || name.length() > ValidationConstants.NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.NAME_LENGTH_ERROR);
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.DESCRIPTION_CANNOT_BE_BLANK);
        }
        if (description.length() < ValidationConstants.DESCRIPTION_MIN_LENGTH || description.length() > ValidationConstants.DESCRIPTION_MAX_LENGTH_STATE_CITY) {
            throw new IllegalArgumentException(ErrorMessages.DESCRIPTION_LENGTH_ERROR_STATE_CITY);
        }
    }
}

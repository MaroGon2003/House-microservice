package com.powerup.house_microservice.domain.utils;

public class StateCityValidationUtils {

    private StateCityValidationUtils() {}

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(DomainConstants.NAME_CANNOT_BE_BLANK);
        }
        if (name.length() < DomainConstants.NAME_MIN_LENGTH || name.length() > DomainConstants.NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(DomainConstants.NAME_LENGTH_ERROR);
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(DomainConstants.DESCRIPTION_CANNOT_BE_BLANK);
        }
        if (description.length() < DomainConstants.DESCRIPTION_MIN_LENGTH || description.length() > DomainConstants.DESCRIPTION_MAX_LENGTH_STATE_CITY) {
            throw new IllegalArgumentException(DomainConstants.DESCRIPTION_LENGTH_ERROR_STATE_CITY);
        }
    }
}

package com.powerup.house_microservice.application.utils;

import com.powerup.house_microservice.domain.utils.ErrorMessages;

public class ValidationMessages {

    //private constructor to prevent instantiation
    private ValidationMessages() {
        throw new UnsupportedOperationException(ErrorMessages.UTILITY_CLASS_INSTANTIATION_ERROR);
    }
    public static final String NAME_SIZE_LOCATION = "The name must be between 2 and 50 characters";
    public static final String DESCRIPTION_SIZE_LOCATION = "The description must be between 2 and 120 characters";
    public static final String NAME_SIZE_REALESTATE = "The name must be between 2 and 50 characters";
    public static final String DESCRIPTION_SIZE_REALESTATE = "The description must be a maximum of 90 characters";
}

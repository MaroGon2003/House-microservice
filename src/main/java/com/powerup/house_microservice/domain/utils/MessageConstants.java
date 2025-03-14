package com.powerup.house_microservice.domain.utils;

public class MessageConstants {

    //private constructor to prevent instantiation
    private MessageConstants() {
        throw new UnsupportedOperationException(ErrorMessages.UTILITY_CLASS_INSTANTIATION_ERROR);
    }

    //LOCATION
    public static final String SEARCH_BY_CITY = "city";
    public static final String SEARCH_BY_STATE = "state";

    //PAGINATION
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

}

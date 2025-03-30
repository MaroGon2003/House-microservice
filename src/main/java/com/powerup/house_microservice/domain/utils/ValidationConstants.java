package com.powerup.house_microservice.domain.utils;

public class ValidationConstants {

    private ValidationConstants() {
        // Private constructor to prevent instantiation
    }

    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MIN_LENGTH = 2;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int DESCRIPTION_MAX_LENGTH_STATE_CITY = 120;


}

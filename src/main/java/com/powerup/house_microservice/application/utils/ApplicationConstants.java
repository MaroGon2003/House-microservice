package com.powerup.house_microservice.application.utils;

public class ApplicationConstants {

    private ApplicationConstants() {}

    // CITY AND STATE REQUEST
    public static final int NAME_MIN_SIZE_CITY_STATE = 2;
    public static final int NAME_MAX_SIZE_CITY_STATE = 50;
    public static final int DESCRIPTION_MIN_SIZE_CITY_STATE = 2;
    public static final int DESCRIPTION_MAX_SIZE_CITY_STATE = 120;

    public static final String NAME_SIZE_LOCATION = "The name must be between " + NAME_MIN_SIZE_CITY_STATE + " and " + NAME_MAX_SIZE_CITY_STATE + " characters";
    public static final String DESCRIPTION_SIZE_LOCATION = "The description must be between " + DESCRIPTION_MIN_SIZE_CITY_STATE + " and " + DESCRIPTION_MAX_SIZE_CITY_STATE + " characters";


    // REAL ESTATE CATEGORY REQUEST
    public static final int NAME_MIN_SIZE_REAL_ESTATE_CATEGORY = 2;
    public static final int NAME_MAX_SIZE_REAL_ESTATE_CATEGORY = 50;
    public static final int DESCRIPTION_MAX_SIZE_REAL_ESTATE_CATEGORY = 90;

    public static final String NAME_SIZE_REAL_ESTATE_CATEGORY = "The name must be between " + NAME_MIN_SIZE_REAL_ESTATE_CATEGORY + " and " + NAME_MAX_SIZE_REAL_ESTATE_CATEGORY + " characters";
    public static final String DESCRIPTION_SIZE_REAL_ESTATE_CATEGORY = "The description must not exceed " + DESCRIPTION_MAX_SIZE_REAL_ESTATE_CATEGORY + " characters";


    // REAL ESTATE REQUEST
    public static final int NAME_MAX_SIZE_REAL_ESTATE_REQUEST = 100;
    public static final int DESCRIPTION_MAX_SIZE_REAL_ESTATE_REQUEST = 500;
    public static final int MIN_ROOMS_COUNT = 1;
    public static final int MIN_BATHROOMS_COUNT = 1;
    public static final String PRICE_MIN_VALUE = "0.01";
    public static final String NAME_REQUIRED_MESSAGE = "The name is required.";
    public static final String NAME_SIZE_REAL_ESTATE_REQUEST_MESSAGE = "The name must not exceed " + NAME_MAX_SIZE_REAL_ESTATE_REQUEST + " characters.";
    public static final String DESCRIPTION_REQUIRED_MESSAGE = "The description is required.";
    public static final String DESCRIPTION_SIZE_REAL_ESTATE_REQUEST_MESSAGE = "The description must not exceed " + DESCRIPTION_MAX_SIZE_REAL_ESTATE_REQUEST + " characters.";
    public static final String CATEGORY_ID_REQUIRED_MESSAGE = "The category ID is required.";
    public static final String ROOMS_COUNT_MIN_MESSAGE = "The property must have at least " + MIN_ROOMS_COUNT + " room.";
    public static final String BATHROOMS_COUNT_MIN_MESSAGE = "The property must have at least " + MIN_BATHROOMS_COUNT + " bathroom.";
    public static final String PRICE_REQUIRED_MESSAGE = "The price is required.";
    public static final String PRICE_MIN_MESSAGE = "The price must be greater than zero.";
    public static final String LOCATION_ID_REQUIRED_MESSAGE = "The location ID is required.";
    public static final String ACTIVE_FROM_REQUIRED_MESSAGE = "The activeFrom date is required.";


    // CONTENT DYNAMIC
    public static final String LOCATIONS = "locations";
    public static final String REAL_ESTATE_CATEGORIES = "realEstateCategories";
    public static final String REAL_ESTATE = "realEstates";
    public static final String DEFAULT_CONTENT = "content";

}

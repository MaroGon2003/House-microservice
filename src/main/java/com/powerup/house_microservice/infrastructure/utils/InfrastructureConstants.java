package com.powerup.house_microservice.infrastructure.utils;

public class InfrastructureConstants {

    private InfrastructureConstants() {
        // Private constructor to prevent instantiation
    }

    //HTTP STATUS CODES
    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_500 = "500";


    // CityController constants
    public static final String CITY_CONTROLLER_REQUEST_MAPPING = "/cities";
    public static final String CITY_CONTROLLER_TAG_NAME = "Cities";
    public static final String CITY_CONTROLLER_TAG_DESCRIPTION = "Endpoints for managing cities";
    public static final String CITY_CONTROLLER_OPERATION_SUMMARY = "Create a new city";
    public static final String CITY_CONTROLLER_OPERATION_DESCRIPTION = "Creates a new city and returns a 201 Created response.";
    public static final String CITY_CONTROLLER_RESPONSE_201_DESCRIPTION = "City created successfully";
    public static final String CITY_CONTROLLER_RESPONSE_400_DESCRIPTION = "Invalid request body";
    public static final String CITY_CONTROLLER_RESPONSE_500_DESCRIPTION = "Internal server error";
    public static final String CITY_CONTROLLER_POST_MAPPING = "/states/{stateId}/cities";


    // LocationController constants
    public static final String LOCATION_CONTROLLER_REQUEST_MAPPING = "/locations";
    public static final String LOCATION_CONTROLLER_TAG_NAME = "Locations";
    public static final String LOCATION_CONTROLLER_TAG_DESCRIPTION = "Endpoints for managing locations";
    public static final String LOCATION_CONTROLLER_OPERATION_CREATE_SUMMARY = "Create location";
    public static final String LOCATION_CONTROLLER_OPERATION_CREATE_DESCRIPTION = "Create a new location";
    public static final String LOCATION_CONTROLLER_OPERATION_GET_SUMMARY = "Get locations";
    public static final String LOCATION_CONTROLLER_OPERATION_GET_DESCRIPTION = "Retrieve a paginated list of locations filtered by state and city name";
    public static final String LOCATION_CONTROLLER_RESPONSE_201_DESCRIPTION = "Location created successfully";
    public static final String LOCATION_CONTROLLER_RESPONSE_200_DESCRIPTION = "List of locations retrieved successfully";
    public static final String LOCATION_CONTROLLER_RESPONSE_400_DESCRIPTION = "Invalid parameters";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_ASCENDING = "true";


    // RealEstateCategoryController constants
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_REQUEST_MAPPING = "/real-estate-categories";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_TAG_NAME = "Real Estate Categories";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_TAG_DESCRIPTION = "Endpoints for managing real estate categories";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_CREATE_SUMMARY = "Create a new real estate category";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_CREATE_DESCRIPTION = "Creates a new real estate category and returns a 201 Created response.";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_GET_SUMMARY = "Get all real estate categories";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_OPERATION_GET_DESCRIPTION = "Retrieves a paginated list of all real estate categories, with sorting options.";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_201_DESCRIPTION = "Category created successfully";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_200_DESCRIPTION = "List of categories retrieved successfully";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_400_DESCRIPTION = "Invalid request parameters";
    public static final String REAL_ESTATE_CATEGORY_CONTROLLER_RESPONSE_500_DESCRIPTION = "Internal server error";


    // RealEstateController constants
    public static final String REAL_ESTATE_CONTROLLER_REQUEST_MAPPING = "/real-estates";
    public static final String REAL_ESTATE_CONTROLLER_TAG_NAME = "Real Estates";
    public static final String REAL_ESTATE_CONTROLLER_TAG_DESCRIPTION = "Endpoints for managing real estates";
    public static final String REAL_ESTATE_CONTROLLER_OPERATION_SUMMARY = "Create a new real estate";
    public static final String REAL_ESTATE_CONTROLLER_OPERATION_DESCRIPTION = "Creates a new real estate and returns a 201 Created response.";
    public static final String REAL_ESTATE_CONTROLLER_RESPONSE_201_DESCRIPTION = "Real estate created successfully";
    public static final String REAL_ESTATE_CONTROLLER_RESPONSE_400_DESCRIPTION = "Invalid request body";
    public static final String REAL_ESTATE_CONTROLLER_RESPONSE_500_DESCRIPTION = "Internal server error";
    // RealEstateController operation constants
    public static final String REAL_ESTATE_CONTROLLER_OPERATION_GET_SUMMARY = "Get real estates";
    public static final String REAL_ESTATE_CONTROLLER_OPERATION_GET_DESCRIPTION = "Retrieve a paginated list of real estates filtered by various criteria";
    public static final String REAL_ESTATE_CONTROLLER_RESPONSE_200_DESCRIPTION = "List of real estates retrieved successfully";
    public static final String DEFAULT_MIN_PRICE = "0.0";
    public static final String REAL_ESTATE_CONTROLLER_EXISTS_BY_ID = "/exists";
    public static final String REAL_ESTATE_CONTROLLER_OPERATION_EXISTS_BY_ID_SUMMARY = "Check if a real estate exists by ID";
    public static final String REAL_ESTATE_CONTROLLER_OPERATION_EXISTS_BY_ID_DESCRIPTION = "Verifies if a real estate with the given ID exists in the system.";
    public static final String REAL_ESTATE_CONTROLLER_RESPONSE_200_EXISTS_DESCRIPTION = "Real estate existence verified successfully";
    public static final String REAL_ESTATE_CONTROLLER_RESPONSE_400_EXISTS_DESCRIPTION = "Invalid request parameter";
    public static final String REAL_ESTATE_CONTROLLER_RESPONSE_500_EXISTS_DESCRIPTION = "Internal server error";


    // StateController constants
    public static final String STATE_CONTROLLER_REQUEST_MAPPING = "/states";
    public static final String STATE_CONTROLLER_TAG_NAME = "States";
    public static final String STATE_CONTROLLER_TAG_DESCRIPTION = "Endpoints for managing states";
    public static final String STATE_CONTROLLER_OPERATION_SUMMARY = "Create a new state";
    public static final String STATE_CONTROLLER_OPERATION_DESCRIPTION = "Creates a new state and returns a 201 Created response.";
    public static final String STATE_CONTROLLER_RESPONSE_201_DESCRIPTION = "State created successfully";
    public static final String STATE_CONTROLLER_RESPONSE_400_DESCRIPTION = "Invalid request body";
    public static final String STATE_CONTROLLER_RESPONSE_500_DESCRIPTION = "Internal server error";

    //Sort direction constants
    public static final String SORT_DIRECTION_ASC = "ASC";
    public static final String SORT_BY_CITY_NAME = "city";
    public static final String SORT_BY_NAME = "name";

    //JWT
    public static final String TOKEN_INVALID =  "Token is invalid";
    public static final String ROLE_CLAIM = "role";

    // RealEstateJpaAdapter constants
    public static final String FIELD_ID = "id";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_LOCATION = "location";
    public static final String FIELD_CITY = "city";
    public static final String FIELD_STATE = "state";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_CATEGORY = "category";
    public static final String FIELD_ROOMS_COUNT = "roomsCount";
    public static final String FIELD_BATHROOMS_COUNT = "bathroomsCount";
    public static final String FIELD_PRICE = "price";

}

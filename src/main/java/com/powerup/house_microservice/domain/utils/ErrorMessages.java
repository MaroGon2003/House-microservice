package com.powerup.house_microservice.domain.utils;

public class ErrorMessages {

    //Constructos para evitar instanciacion
    private ErrorMessages() {}

    //REAL ESTATE CATEGORY
    public static final String REAL_ESTATE_CATEGORY_ALREADY_EXISTS = "Real estate category with name: %s already exists";
    public static final String REAL_ESTATE_CATEGORY_NOT_NULL = "Real estate category cannot be null";

    //LOCATION
    public static final String STATE_NOT_FOUND = "State with id: %s was not found";
    public static final String STATE_ALREADY_EXIST = "State with name: %s already exists";
    public static final String CITY_NOT_FOUND = "City with id: %s was not found";

    //PAGINATION MESSAGES CONSTANTS VALIDATION
    public static final String UTILITY_CLASS_INSTANTIATION_ERROR = "This is a utility class and cannot be instantiated";
    public static final String PAGE_INDEX_NEGATIVE_ERROR = "Page index must not be negative";
    public static final String PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR = "Page size must be greater than zero";
    public static final String SORT_DIRECTION_INVALID_ERROR = "Sort direction must be 'ASC' or 'DESC'";

    //REAL ESTATE
    public static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank";
    public static final String NAME_LENGTH_ERROR = "Name must be between 2 and 50 characters";
    public static final String DESCRIPTION_CANNOT_BE_BLANK = "Description cannot be blank";
    public static final String DESCRIPTION_LENGTH_ERROR = "Description must be between 2 and 90 characters";

    //STATE CITY
    public static final String DESCRIPTION_LENGTH_ERROR_STATE_CITY = "Description must be between 2 and 120 characters";


}

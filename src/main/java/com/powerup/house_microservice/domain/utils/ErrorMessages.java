package com.powerup.house_microservice.domain.utils;

public class ErrorMessages {

    //Constructos para evitar instanciacion
    private ErrorMessages() {
        throw new IllegalStateException("This class cannot be instantiated");
    }

    //REAL ESTATE CATEGORY
    public static final String REAL_ESTATE_CATEGORY_ALREADY_EXISTS = "Real estate category already exists";
    public static final String REAL_ESTATE_CATEGORY_NOT_NULL = "Real estate category cannot be null";
    public static final String REAL_ESTATE_CATEGORY_NOT_FOUND = "Real estate category not found";

    //LOCATION
    public static final String STATE_NOT_FOUND = "State not found";
    public static final String STATE_ALREADY_EXIST = "State already exist";
    public static final String STATE_OR_CITY_NULL = "State or city cannot be null";
    public static final String LOCATION_ALREADY_EXIST = "Location already exist";
    public static final String LOCATION_NOT_FOUND = "Location not found";
    public static final String INVALID_SEARCH_BY = "Invalid search by";

    //PAGINATION MESSAGES CONSTANTS VALIDATION
    public static final String UTILITY_CLASS_INSTANTIATION_ERROR = "This is a utility class and cannot be instantiated";
    public static final String PAGE_INDEX_NEGATIVE_ERROR = "Page index must not be negative";
    public static final String PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR = "Page size must be greater than zero";
    public static final String SORT_BY_NULL_OR_EMPTY_ERROR = "Sort by parameter must not be null or empty";
    public static final String SORT_DIRECTION_INVALID_ERROR = "Sort direction must be 'ASC' or 'DESC'";


}

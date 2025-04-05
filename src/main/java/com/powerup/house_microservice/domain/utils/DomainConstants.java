package com.powerup.house_microservice.domain.utils;

public class DomainConstants {

    private DomainConstants() {
        // Private constructor to prevent instantiation
    }

    //ERRORS MESSAGES -----------------------------------------------------------------------------------------------------------------

    //REAL ESTATE CATEGORY
    public static final String REAL_ESTATE_CATEGORY_ALREADY_EXISTS = "Real estate category with name: %s already exists";
    public static final String REAL_ESTATE_CATEGORY_NOT_NULL = "Real estate category cannot be null";

    //LOCATION
    public static final String STATE_NOT_FOUND = "State with id: %s was not found";
    public static final String STATE_ALREADY_EXIST = "State with name: %s already exists";
    public static final String CITY_NOT_FOUND = "City with id: %s was not found";
    public static final String LOCATION_NOT_FOUND = "Location with id: %s was not found";


    //PAGINATION MESSAGES CONSTANTS VALIDATION
    public static final String UTILITY_CLASS_INSTANTIATION_ERROR = "This is a utility class and cannot be instantiated";
    public static final String PAGE_INDEX_NEGATIVE_ERROR = "Page index must not be negative";
    public static final String PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR = "Page size must be greater than zero";
    public static final String SORT_DIRECTION_INVALID_ERROR = "Sort direction must be 'ASC' or 'DESC'";

    //REAL ESTATE CATEGORY
    public static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank";
    public static final String NAME_LENGTH_ERROR = "Name must be between 2 and 50 characters";
    public static final String DESCRIPTION_CANNOT_BE_BLANK = "Description cannot be blank";
    public static final String DESCRIPTION_LENGTH_ERROR = "Description must be between 2 and 90 characters";
    public static final String REAL_ESTATE_CATEGORY_NOT_FOUND = "Real estate category with id: %s was not found";

    //STATE CITY
    public static final String DESCRIPTION_LENGTH_ERROR_STATE_CITY = "Description must be between 2 and 120 characters";

    //REAL ESTATE
    public static final String PUBLICATION_DATE_NOT_IN_PAST = "Publication date must be in the future";
    public static final String PUBLICATION_DATE_NOT_MORE_THAN_1_MONTH = "Publication date must be less than 1 month from today";


    //PAGINAION CONSTANTAS ----------------------------------------------------------------------------------------------------------------------------------------
    public static final int MIN_PAGE_INDEX = 0;
    public static final int MIN_PAGE_SIZE = 1;
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    //VALIDATION CONSTANTS -----------------------------------------------------------------------------------------------------------------------------------


    //REAL ESTATE
    public static final int MAX_MONTHS = 1;

    //CITY AND STATE
    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MIN_LENGTH = 2;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int DESCRIPTION_MAX_LENGTH_STATE_CITY = 120;

}

package com.powerup.house_microservice.domain.utils;

public class MessageConstants {

    //Constructos para evitar instanciacion
    private MessageConstants() {
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


}

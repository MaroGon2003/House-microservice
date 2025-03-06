package com.powerup.house_microservice.domain.utils;

public class MessageConstants {

    //Constructos para evitar instanciacion
    private MessageConstants() {
        throw new IllegalStateException("This class cannot be instantiated");
    }

    public static final String REAL_ESTATE_CATEGORY_ALREADY_EXISTS = "Real estate category already exists";
    public static final String REAL_ESTATE_CATEGORY_NOT_NULL = "Real estate category cannot be null";

}

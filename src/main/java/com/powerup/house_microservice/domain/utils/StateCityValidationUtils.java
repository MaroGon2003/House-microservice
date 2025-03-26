package com.powerup.house_microservice.domain.utils;

public class StateCityValidationUtils {

    private StateCityValidationUtils() {
    }

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Name must be between 2 and 50 characters");
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank");
        }
        if (description.length() < 2 || description.length() > 120) {
            throw new IllegalArgumentException("Description must be between 2 and 120 characters");
        }
    }
}

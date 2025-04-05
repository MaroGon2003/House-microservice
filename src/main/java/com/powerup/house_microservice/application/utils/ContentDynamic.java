package com.powerup.house_microservice.application.utils;

import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;

import java.util.HashMap;
import java.util.Map;

public class ContentDynamic {

    private ContentDynamic() {
        // Private constructor to prevent instantiation
    }

    private static final Map<Class<?>, String> typeToPropertyNameMap = new HashMap<>();

    static {
        typeToPropertyNameMap.put(LocationResponseDto.class, ApplicationConstants.LOCATIONS);
        typeToPropertyNameMap.put(RealEstateCategoryResponseDto.class, ApplicationConstants.REAL_ESTATE_CATEGORIES);
    }

    public static String getPropertyName(Class<?> clazz) {
        return typeToPropertyNameMap.getOrDefault(clazz, ApplicationConstants.DEFAULT_CONTENT);
    }
}


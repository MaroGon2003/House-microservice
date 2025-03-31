package com.powerup.house_microservice.application.utils;

import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;

import java.util.HashMap;
import java.util.Map;

public class ContentDynamic {

    private static final String LOCATIONS = "locations";
    private static final String REAL_ESTATE_CATEGORIES = "realEstateCategories";
    private static final String DEFAULT_CONTENT = "content";

    private static final Map<Class<?>, String> typeToPropertyNameMap = new HashMap<>();

    static {
        typeToPropertyNameMap.put(LocationResponseDto.class, LOCATIONS);
        typeToPropertyNameMap.put(RealEstateCategoryResponseDto.class, REAL_ESTATE_CATEGORIES);
    }

    public static String getPropertyName(Class<?> clazz) {
        return typeToPropertyNameMap.getOrDefault(clazz, DEFAULT_CONTENT);
    }

    private ContentDynamic() {}

}


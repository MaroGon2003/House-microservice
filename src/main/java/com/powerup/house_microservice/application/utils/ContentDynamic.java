package com.powerup.house_microservice.application.utils;

import com.powerup.house_microservice.application.dto.response.LocationResponseDto;
import com.powerup.house_microservice.application.dto.response.RealEstateCategoryResponseDto;

import java.util.HashMap;
import java.util.Map;

public class ContentDynamic {

    private static final Map<Class<?>, String> typeToPropertyNameMap = new HashMap<>();

    static {
        typeToPropertyNameMap.put(LocationResponseDto.class, "locations");
        typeToPropertyNameMap.put(RealEstateCategoryResponseDto.class, "realEstates");
        // Add other mappings as needed
    }

    public static String getPropertyName(Class<?> clazz) {
        return typeToPropertyNameMap.getOrDefault(clazz, "content");
    }

    private ContentDynamic() {}


}


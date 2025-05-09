package com.powerup.house_microservice.application.utils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagedResult<T> {

    @JsonIgnore
    private final List<T> content;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;

    public PagedResult(List<T> content, int page, int size) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = content.size();
        this.totalPages = (int) Math.ceil((double) totalElements / size);
    }

    @JsonAnyGetter
    public Map<String, Object> getContent() {
        Map<String, Object> contentMap = new HashMap<>();
        if (content.isEmpty()) {
            contentMap.put(ApplicationConstants.DEFAULT_CONTENT, List.of());
        } else {
            String propertyName = ContentDynamic.getPropertyName(content.get(0).getClass());
            contentMap.put(propertyName, content);
        }
        return contentMap;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

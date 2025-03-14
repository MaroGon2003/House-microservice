package com.powerup.house_microservice.application.utils;

import java.util.List;

public class PagedResult<T> {

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

    public List<T> getContent() {
        return content;
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

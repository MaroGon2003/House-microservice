package com.powerup.house_microservice.domain.model;

import java.math.BigDecimal;

public class RealEstateFilter {
    private String stateName;
    private String cityName;
    private Long categoryId;
    private Integer rooms;
    private Integer bathrooms;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int page;
    private int size;
    private String sortDirection;

    // Getters and Setters
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return "RealEstateFilter{" +
                "stateName='" + stateName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", categoryId=" + categoryId +
                ", rooms=" + rooms +
                ", bathrooms=" + bathrooms +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", sortDirection='" + sortDirection + '\'' +
                '}';
    }

}
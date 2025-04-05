package com.powerup.house_microservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RealEstateModel {

    private Long id;
    private String name;
    private String description;
    private RealEstateCategoryModel category;
    private int roomsCount;
    private int bathroomsCount;
    private BigDecimal price;
    private LocationModel location;
    private LocalDate activeFrom;
    private LocalDate createdOn;
    private ListingStatus status;

    public RealEstateModel() {
    }

    public RealEstateModel(String name, String description, RealEstateCategoryModel category, int roomsCount,
                           int bathroomsCount, BigDecimal price, LocationModel location, LocalDate activeFrom) {
        this();
        this.name = name;
        this.description = description;
        this.category = category;
        this.roomsCount = roomsCount;
        this.bathroomsCount = bathroomsCount;
        this.price = price;
        this.location = location;
        this.activeFrom = activeFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RealEstateCategoryModel getCategory() {
        return category;
    }

    public void setCategory(RealEstateCategoryModel category) {
        this.category = category;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getBathroomsCount() {
        return bathroomsCount;
    }

    public void setBathroomsCount(int bathroomsCount) {
        this.bathroomsCount = bathroomsCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public LocalDate getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(LocalDate activeFrom) {
        this.activeFrom = activeFrom;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public ListingStatus getStatus() {
        return status;
    }

    public void setStatus(ListingStatus status) {
        this.status = status;
    }
}

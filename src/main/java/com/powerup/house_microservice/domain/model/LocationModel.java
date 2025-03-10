package com.powerup.house_microservice.domain.model;

public class LocationModel {

    private Long id;
    private String city;
    private String state;

    public LocationModel() {
    }

    public LocationModel(Long id, String city, String state) {
        this.id = id;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

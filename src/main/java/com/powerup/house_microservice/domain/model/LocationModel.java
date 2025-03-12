package com.powerup.house_microservice.domain.model;

public class LocationModel {
    private CityModel city;
    private StateModel state;

    public LocationModel() {
    }

    public LocationModel(CityModel city, StateModel state) {
        this.city = city;
        this.state = state;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public StateModel getState() {
        return state;
    }

    public void setState(StateModel state) {
        this.state = state;
    }
}

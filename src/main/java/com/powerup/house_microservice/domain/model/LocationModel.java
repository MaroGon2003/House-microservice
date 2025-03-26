package com.powerup.house_microservice.domain.model;

public class LocationModel {

    private Long id;
    private CityModel city;

    private String neighborhood;

    public LocationModel() {
    }

    public LocationModel(Long id, CityModel city, String neighborhood) {
        this.id = id;
        this.city = city;
        this.neighborhood = neighborhood;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}

package com.powerup.house_microservice.domain.model;

public class CityModel {

    private Long id;
    private String name;
    private String description;
    private StateModel state;

    public CityModel() {
    }

    public CityModel(Long id, String name, String description, StateModel state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
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

    public StateModel getState() {
        return state;
    }

    public void setState(StateModel state) {
        this.state = state;
    }

}

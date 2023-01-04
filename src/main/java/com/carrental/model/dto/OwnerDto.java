package com.carrental.model.dto;


import com.carrental.model.entity.Car;

import java.util.Collection;

public class OwnerDto {
    private Long id;
    private String name;

    Collection<Car> cars;

    public OwnerDto() {
    }

    public OwnerDto(Long id, String name, Collection<Car> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
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

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}

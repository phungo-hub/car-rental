package com.carrental.model.dto;


import com.carrental.model.entity.Car;
import lombok.Data;

import java.util.Collection;
@Data
public class OwnerDto {
    private Long id;
    private String name;
    Collection<Car> cars;
}

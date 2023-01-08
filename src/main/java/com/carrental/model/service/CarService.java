package com.carrental.model.service;

import com.carrental.model.dto.CarDto;
import com.carrental.model.dto.CategoryDto;
import com.carrental.model.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService extends GeneralService<CarDto>{

    Page<Car> findAllCars(Pageable pageable);
    Iterable<CarDto> findAll();

    Page<Car> findAll(Pageable pageable);

    Iterable<CarDto> findAllByCategory(CategoryDto categoryDto);
    Page<Car> findAllByNameContaining(String carName, Pageable pageable);

}

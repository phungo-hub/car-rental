package com.carrental.model.service.impl;

import com.carrental.model.dto.CarDto;
import com.carrental.model.entity.Car;
import com.carrental.model.repository.CarRepository;
import com.carrental.model.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<CarDto> findAll() {
        Iterable<Car> entities = carRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, CarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarDto> findById(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        return Optional.of(modelMapper.map(car, CarDto.class));
    }

    @Override
    public void save(CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        carRepository.save(car);
    }

    @Override
    public void remove(Long id) {
        carRepository.deleteById(id);
    }
}

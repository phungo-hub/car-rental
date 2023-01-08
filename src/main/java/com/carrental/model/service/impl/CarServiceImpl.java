package com.carrental.model.service.impl;

import com.carrental.model.dto.CarDto;
import com.carrental.model.dto.CategoryDto;
import com.carrental.model.entity.Car;
import com.carrental.model.repository.CarRepository;
import com.carrental.model.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@PropertySource("classpath:application.properties")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<Car> findAllCars(Pageable pageable) {
        List<Car> cars = carRepository.findAll();
        return new PageImpl<>(cars);
    }

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
        MultipartFile multipartFile = carDto.getImg();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(carDto.getImg().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Car car = modelMapper.map(carDto, Car.class);
        car.setImg(fileName);
        carRepository.save(car);
    }

    @Override
    public void remove(Long id) {
        carRepository.deleteById(id);
    }

    public Iterable<CarDto> findAllByCategory(CategoryDto categoryDto) {
        Iterable<Car> entities = carRepository.findAllByCategory(categoryDto);
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, CarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Car> findAll(Pageable pageable) {
        List<Car> entities = carRepository.findAll();
        return new PageImpl<>(entities);
    }

    @Override
    public Page<Car> findAllByNameContaining(String carName, Pageable pageable) {
        Page<Car> entities = carRepository.findAllByNameContaining(carName, pageable);
        List<Car> dtos = entities.getContent();
        return new PageImpl<>(dtos);
    }
}

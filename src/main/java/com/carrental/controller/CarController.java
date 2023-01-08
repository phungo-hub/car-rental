package com.carrental.controller;

import com.carrental.model.dto.CarDto;
import com.carrental.model.dto.CategoryDto;
import com.carrental.model.dto.OwnerDto;
import com.carrental.model.entity.Car;
import com.carrental.model.service.CarService;
import com.carrental.model.service.CategoryService;
import com.carrental.model.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("ownerDtos")
    public Iterable<OwnerDto> ownerDtos() {
        return ownerService.findAll();
    }

    @ModelAttribute("catDtos")
    public Iterable<CategoryDto> catDtos() {
        return categoryService.findAll();
    }

    @GetMapping({"/list", ""})
    public ModelAndView listCars(@RequestParam("search") Optional<String> search,
                                 @PageableDefault(value = 5) Pageable pageable) {
//        Page<CarDto> carDtos;
//        if (search.isPresent()) {
//            carDtos = carService.findAllByNameContaining(search.get(), pageable);
//        } else {
//            carDtos = carService.findAll(pageable);
//        }
        Page<Car> cars;
        if (search.isPresent()) {
            cars = carService.findAllByNameContaining(search.get(), pageable);
        } else {
            cars =  carService.findAllCars(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/cars/list");
        modelAndView.addObject("carDtos", cars);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateCar() {
        ModelAndView modelAndView = new ModelAndView("/cars/create");
        modelAndView.addObject("carDto", new CarDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCar(@ModelAttribute CarDto carDto) {
        carService.save(carDto);
        ModelAndView modelAndView = new ModelAndView("/cars/create");
        modelAndView.addObject("carDto", new CarDto());
        modelAndView.addObject("message", "Car has been added successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<CarDto> carDto = carService.findById(id);
        ModelAndView modelAndView;
        if (carDto.isPresent()) {
            modelAndView = new ModelAndView("/cars/edit");
            modelAndView.addObject("carDto",carDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateCar(@ModelAttribute("carDto") CarDto carDto) {
        carService.save(carDto);
        ModelAndView modelAndView = new ModelAndView("/cars/edit");
        modelAndView.addObject("carDto", carDto);
        modelAndView.addObject("message", "car has been updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<CarDto> carDto = carService.findById(id);
        ModelAndView modelAndView;
        if (carDto.isPresent()) {
            modelAndView = new ModelAndView("/cars/delete");
            modelAndView.addObject("carDto", carDto.get());
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteCar(@ModelAttribute("car") CarDto carDto) {
        carService.remove(carDto.getId());
        return "redirect:/car/list";
    }
}

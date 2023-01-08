package com.carrental.controller;


import com.carrental.model.dto.CarDto;
import com.carrental.model.dto.CategoryDto;
import com.carrental.model.dto.OwnerDto;
import com.carrental.model.service.CarService;
import com.carrental.model.service.CategoryService;
import com.carrental.model.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CarService carService;

    @Autowired
    OwnerService ownerService;

    @GetMapping({"/list", ""})
    public ModelAndView listOwners(@RequestParam("search") Optional<String> search,
                                 Pageable pageable) {
        Page<OwnerDto> ownerDtos;
        if (search.isPresent()) {
            ownerDtos = ownerService.findAllByNameContaining(search.get(), pageable);
        } else {
            ownerDtos = ownerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/owners/list");
        modelAndView.addObject("owners", ownerDtos);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/owners/create");
        modelAndView.addObject("ownerDto", new OwnerDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCategory(@ModelAttribute("ownerDto") OwnerDto ownerDto) {
        ownerService.save(ownerDto);
        ModelAndView modelAndView = new ModelAndView("/owners/create");
        modelAndView.addObject("ownerDto", new OwnerDto());
        modelAndView.addObject("message","Create owner successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<OwnerDto> ownerDto = ownerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/owners/edit");
        modelAndView.addObject("ownerDto", ownerDto.get());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editCategory(@ModelAttribute("ownerDto") OwnerDto ownerDto) {
        ownerService.save(ownerDto);
        ModelAndView modelAndView = new ModelAndView("/owners/edit");
        modelAndView.addObject("ownerDto", ownerDto);
        modelAndView.addObject("message", "Owner has been edit successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<OwnerDto> ownerDto = ownerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/owners/delete");
        modelAndView.addObject("ownerDto", ownerDto.get());
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteCar(@ModelAttribute("ownerDto") OwnerDto ownerDto) {
        ownerService.remove(ownerDto.getId());
        return "redirect:/owner/list";
    }
}
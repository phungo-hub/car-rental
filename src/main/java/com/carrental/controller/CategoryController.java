package com.carrental.controller;

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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CarService carService;

    @Autowired
    OwnerService ownerService;

    @GetMapping({"/list", ""})
    public ModelAndView listCategories(@RequestParam("search") Optional<String> search) {
        Iterable<CategoryDto> categoryDtos;
        if (search.isPresent()) {
            categoryDtos = categoryService.findAllByNameContaining(search.get());
        } else {
            categoryDtos = categoryService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("/categories/list");
        modelAndView.addObject("categories", categoryDtos);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/categories/create");
        modelAndView.addObject("categoryDto", new CategoryDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        ModelAndView modelAndView = new ModelAndView("/categories/create");
        modelAndView.addObject("categoryDto", new CategoryDto());
        modelAndView.addObject("message","Create category successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<CategoryDto> categoryDto = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/categories/edit");
        modelAndView.addObject("categoryDto", categoryDto.get());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        ModelAndView modelAndView = new ModelAndView("/categories/edit");
        modelAndView.addObject("categoryDto", categoryDto);
        modelAndView.addObject("message", "Category has been edit successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<CategoryDto> categoryDto = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/categories/delete");
        modelAndView.addObject("categoryDto", categoryDto.get());
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteCar(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.remove(categoryDto.getId());
        return "redirect:/category/list";
    }
}

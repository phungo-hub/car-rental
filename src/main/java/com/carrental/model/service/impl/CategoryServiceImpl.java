package com.carrental.model.service.impl;

import com.carrental.model.dto.CategoryDto;
import com.carrental.model.entity.Category;
import com.carrental.model.repository.CategoryRepository;
import com.carrental.model.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<CategoryDto> findAll() {
        Iterable<Category> entities = categoryRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        Category entity = categoryRepository.findById(id).orElse(null);
        return Optional.of(modelMapper.map(entity, CategoryDto.class));
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<CategoryDto> findAllByNameContaining(String catName) {
        Iterable<Category> entities = categoryRepository.findAllByNameContaining(catName);
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, CategoryDto.class))
                .collect(Collectors.toList());
    }
}

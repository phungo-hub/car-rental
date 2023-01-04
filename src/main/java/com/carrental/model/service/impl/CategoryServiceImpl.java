package com.carrental.model.service.impl;

import com.carrental.model.dto.CategoryDto;
import com.carrental.model.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public Iterable<CategoryDto> findAll() {
        return null;
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(CategoryDto categoryDto) {

    }

    @Override
    public void remove(Long id) {

    }
}

package com.carrental.model.service;

import com.carrental.model.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService extends GeneralService<CategoryDto>{
    Iterable<CategoryDto> findAllByNameContaining(String catName);
}

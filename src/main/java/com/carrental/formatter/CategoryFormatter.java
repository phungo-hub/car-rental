package com.carrental.formatter;

import com.carrental.model.dto.CategoryDto;
import com.carrental.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<CategoryDto> {
    private CategoryService categoryService;

    @Autowired
    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(CategoryDto object, Locale locale) {
        return null;
    }
}

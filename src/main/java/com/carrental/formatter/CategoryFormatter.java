package com.carrental.formatter;

import com.carrental.model.dto.CategoryDto;
import com.carrental.model.service.CategoryService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryFormatter implements Formatter<CategoryDto> {
    private CategoryService categoryService;

    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto parse(String text, Locale locale) throws ParseException {
        Optional<CategoryDto> categoryDto = categoryService.findById(Long.parseLong(text));
        return categoryDto.orElse(null);
    }

    @Override
    public String print(CategoryDto object, Locale locale) {
        return "[" + object.getId() + ", "
                + object.getName() + "]";
    }
}

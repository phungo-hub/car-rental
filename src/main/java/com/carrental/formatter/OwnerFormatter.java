package com.carrental.formatter;

import com.carrental.model.dto.OwnerDto;
import com.carrental.model.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class OwnerFormatter implements Formatter<OwnerDto> {
    private OwnerService ownerService;

    @Autowired
    public OwnerFormatter(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public OwnerDto parse(String text, Locale locale) throws ParseException {
        Optional<OwnerDto> ownerDto = ownerService.findById(Long.parseLong(text));
        return ownerDto.orElse(null);
    }

    @Override
    public String print(OwnerDto object, Locale locale) {
        return "[" + object.getId() + ", "
                + object.getName() + ", ";
    }
}

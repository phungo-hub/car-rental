package com.carrental.model.service.impl;

import com.carrental.model.dto.OwnerDto;
import com.carrental.model.repository.OwnerRepository;
import com.carrental.model.service.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<OwnerDto> findAll() {
        return null;
    }

    @Override
    public Optional<OwnerDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(OwnerDto ownerDto) {

    }

    @Override
    public void remove(Long id) {

    }
}

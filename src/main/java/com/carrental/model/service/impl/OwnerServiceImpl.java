package com.carrental.model.service.impl;

import com.carrental.model.dto.CarDto;
import com.carrental.model.dto.OwnerDto;
import com.carrental.model.entity.Car;
import com.carrental.model.entity.Owner;
import com.carrental.model.repository.OwnerRepository;
import com.carrental.model.service.OwnerService;
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
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<OwnerDto> findAll() {
        Iterable<Owner> entities = ownerRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, OwnerDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public Page<OwnerDto> findAll(Pageable pageable) {
        List<Owner> entities = ownerRepository.findAll();
        List<OwnerDto> dtos = new ArrayList<>(
                entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, OwnerDto.class))
                        .collect(Collectors.toList())
        );
        return new PageImpl<>(dtos);
    }

    @Override
    public Optional<OwnerDto> findById(Long id) {
        Owner entity = ownerRepository.findById(id).orElse(null);
        return Optional.of(modelMapper.map(entity, OwnerDto.class));
    }

    @Override
    public void save(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        ownerRepository.save(owner);
    }

    @Override
    public void remove(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Page<OwnerDto> findAllByNameContaining(String ownerName, Pageable pageable) {
        Page<Owner> entities = ownerRepository.findAllByNameContaining(ownerName, pageable);
        List<OwnerDto> dtos = new ArrayList<>(
                entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, OwnerDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos);
    }
}

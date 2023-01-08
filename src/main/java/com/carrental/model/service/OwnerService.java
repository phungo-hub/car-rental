package com.carrental.model.service;

import com.carrental.model.dto.CarDto;
import com.carrental.model.dto.OwnerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OwnerService extends GeneralService<OwnerDto>{
    Page<OwnerDto> findAll(Pageable pageable);
    Page<OwnerDto> findAllByNameContaining(String ownerName, Pageable pageable);
}

package com.carrental.model.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class RoleDto {
    private Long id;
    private String name;
    private String desc;
    Collection<UserDto> userDtos;
}

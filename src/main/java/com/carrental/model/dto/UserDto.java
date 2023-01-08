package com.carrental.model.dto;

import com.carrental.model.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private Role role;
    private String fullName;
    private String username;
    private String password;
}

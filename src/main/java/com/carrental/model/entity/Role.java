package com.carrental.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @OneToMany(mappedBy = "role")
    Collection<User> users;
}

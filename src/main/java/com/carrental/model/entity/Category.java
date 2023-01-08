package com.carrental.model.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    Collection<Car> cars;
}

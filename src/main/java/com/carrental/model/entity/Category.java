package com.carrental.model.entity;

import jakarta.persistence.*;
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

    public Category() {
    }

    public Category(String name, Collection<Car> cars) {
        this.name = name;
        this.cars = cars;
    }
}

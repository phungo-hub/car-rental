package com.carrental.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "owners")
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "owner")
    Collection<Car> cars;

    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }
}

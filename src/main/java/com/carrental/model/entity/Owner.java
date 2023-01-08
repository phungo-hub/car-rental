package com.carrental.model.entity;

import javax.persistence.*;
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
}

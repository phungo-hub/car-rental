package com.carrental.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 20)
    private String name;

    @Column(name = "price")
    @Min(value = 30)
    private double price;

    @Column(name = "model")
    @Size(min = 3, max = 20)
    private String model;

    @Column(name = "year")
    @Size(min = 4, max = 4)
    private int year;

    @Column(name = "img")
    private String img;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Owner owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public Car() {
    }

    public Car(Long id, String name, double price, String model, int year, String img, Owner owner, Category category) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.model = model;
        this.year = year;
        this.img = img;
        this.owner = owner;
        this.category = category;
    }

    public Car(String name, double price, String model, int year, String img, Owner owner, Category category) {
        super();
        this.name = name;
        this.price = price;
        this.model = model;
        this.year = year;
        this.img = img;
        this.owner = owner;
        this.category = category;
    }
}

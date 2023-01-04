package com.carrental.model.dto;

import com.carrental.model.entity.Category;
import com.carrental.model.entity.Owner;
import org.springframework.web.multipart.MultipartFile;

public class CarDto {
    private Long id;
    private String name;
    private double price;
    private String model;
    private int year;
    private MultipartFile img;
    private Owner owner;
    private Category category;

    public CarDto() {
    }

    public CarDto(Long id, String name, double price, String model, int year, MultipartFile img, Owner owner, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.model = model;
        this.year = year;
        this.img = img;
        this.owner = owner;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

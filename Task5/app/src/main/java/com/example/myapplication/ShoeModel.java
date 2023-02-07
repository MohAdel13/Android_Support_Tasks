package com.example.myapplication;

public class ShoeModel {
    String brand;
    int size;
    String model;
    String description;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShoeModel(String brand, int size, String model, String description)
    {
        this.brand = brand;
        this.size = size;
        this.model = model;
        this.description = description;
    }
}

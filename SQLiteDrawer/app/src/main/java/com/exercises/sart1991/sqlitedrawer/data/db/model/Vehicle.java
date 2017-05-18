package com.exercises.sart1991.sqlitedrawer.data.db.model;

/**
 * Created by sart1 on 5/15/2017.
 */

public class Vehicle {

    private int id;
    private String brand;
    private int quantity;

    public Vehicle(int id, String brand, int quantity) {
        this.id = id;
        this.brand = brand;
        this.quantity = quantity;
    }

    public Vehicle(String brand, int quantity) {
        this.brand = brand;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

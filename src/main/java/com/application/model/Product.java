package com.application.model;

public class Product {
    private String name;
    private int id;
    private double price;
    private int quantity;
    private int min_quantity;
    private Category category;

    public Product(String name, double price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        min_quantity = 5;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public Category getCategory() {
        return category;
    }
}

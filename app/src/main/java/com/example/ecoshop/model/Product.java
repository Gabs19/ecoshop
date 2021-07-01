package com.example.ecoshop.model;

public class Product {
    private String id;
    private String name;
    private String description;
    private float price;
    private String seller;

    public Product() {
    }

    public Product(String id, String nome, String description, float price, String seller) {
        this.id = id;
        this.name = nome;
        this.description = description;
        this.price = price;
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}

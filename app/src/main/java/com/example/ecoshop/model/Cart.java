package com.example.ecoshop.model;

import java.util.List;

class Cart {

    private String id;
    private List<Product> products;
    private float total;

    public Cart(String id, List<Product> products, float total) {
        this.id = id;
        this.products = products;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}

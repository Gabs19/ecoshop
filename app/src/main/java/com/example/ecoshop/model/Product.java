package com.example.ecoshop.model;

class Product {
    private String nome;
    private String description;
    private float price;

    public Product(String nome, String description, float price) {
        this.nome = nome;
        this.description = description;
        this.price = price;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}

package com.example.ecoshop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String owenerid;
    private List<Product> products = new ArrayList<>();
    private double total;


    public Cart(){

    }

    public Cart(String id, String owenerid, List<Product> products, float total) {
        this.owenerid = owenerid;
        this.products = products;
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total){
        this.total = valorTotal();
    }

    public String getOwenerid() {
        return owenerid;
    }

    public void setOwenerid(String owenerid) {
        this.owenerid = owenerid;
    }


    public double valorTotal(){
        double valor = 0;

        for (int i = 0; i < products.size(); i++){
            valor = valor + products.get(i).getPrice();
        }

        return valor;
    }
}

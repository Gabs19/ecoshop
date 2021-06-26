package com.example.ecoshop.model;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean seller;

    public User() {
    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.seller = false;
    }

    public String getId() { return  id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSeller() {return seller;}

    public void setSeller(boolean seller) {this.seller = seller;}

    @Override
    public String toString() {
        return name;
    }
}

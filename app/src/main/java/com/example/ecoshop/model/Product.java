package com.example.ecoshop.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String id;
    private String name;
    private String description;
    private String date;
    private double price;
    private String type;
    private String sellerId;

    public Product(String id, String name, String description, double price, String type, String seller, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.sellerId = seller;
        this.date = date;
    }

    public Product() {
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        type = in.readString();
        sellerId = in.readString();
        date = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeller() {
        return sellerId;
    }

    public void setSeller(String seller) {
        this.sellerId = seller;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return name ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(type);
        dest.writeString(sellerId);
        dest.writeString(date);
    }
}

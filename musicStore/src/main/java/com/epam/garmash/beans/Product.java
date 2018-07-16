package com.epam.garmash.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Product {

    private int id;
    private String name;
    private String brand;
    private String type;
    private double price;
    private String description;
    private String image;

    public Product(int id, String name, String brand, String type, double price, String description, String image) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != getClass()) return false;
        final Product other = (Product) object;
        return new EqualsBuilder()
                .append(other.id, id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("brand", brand)
                .append("type", type)
                .append("price", price)
                .append("description", description)
                .append("image", image)
                .build();
    }
}

package com.epam.garmash.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Brand {

    private int id;
    private String brand;

    public Brand(int id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Brand other = (Brand) o;
        return new EqualsBuilder()
                .append(other.id, id)
                .append(other.brand, brand)
                .isEquals();

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(brand)
                .build();
    }
}

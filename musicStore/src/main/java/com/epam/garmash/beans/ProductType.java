package com.epam.garmash.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

public class ProductType {

    private int id;
    private String productType;

    public ProductType(int id, String productType) {
        this.id = id;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType other = (ProductType) o;
        return new EqualsBuilder()
                .append(other.id, id)
                .isEquals();

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

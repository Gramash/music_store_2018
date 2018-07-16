package com.epam.garmash.dto;

import com.epam.garmash.beans.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ShoppingCart {

    private Map<Product, Integer> shoppingCart;

    public ShoppingCart() {
        this.shoppingCart = Collections.synchronizedMap(new HashMap<>());
    }

    public void add(Product product) {
        if (shoppingCart.containsKey(product)) {
            shoppingCart.replace(product, shoppingCart.get(product) + 1);
        } else {
            shoppingCart.put(product, 1);
        }
    }

    public void remove(Product product) {
        shoppingCart.remove(product);
    }

    public double getTotalPrice() {
        return shoppingCart.entrySet()
                .stream()
                .map(product -> product.getKey().getPrice() * product.getValue())
                .reduce(0d, (prev, next) -> prev + next);
    }

    public void patchById(int id, int newValue) {
        shoppingCart.entrySet().stream()
                .filter(productIntegerEntry -> productIntegerEntry.getKey().getId() == id)
                .findFirst()
                .ifPresent(productIntegerEntry -> productIntegerEntry.setValue(newValue));
    }

    public Optional<Product> findById(int id) {
        return shoppingCart.keySet().stream().filter(product -> product.getId() == id).findFirst();
    }

    public void clear() {
        shoppingCart.clear();
    }

    public int getCount(Product product) {
        return shoppingCart.get(product);
    }

    public Map<Product, Integer> getCart() {
        return new HashMap<>(shoppingCart);
    }


}

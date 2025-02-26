package org.example.repository;

import org.example.entity.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepo implements Repository<ShoppingCart> {

    private final List<ShoppingCart> shoppingCart = new ArrayList<>();

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCart;
    }

    @Override
    public void add(ShoppingCart cartItem) {
        shoppingCart.add(cartItem);
    }

    @Override
    public void remove(int index) {
        shoppingCart.remove(index);
    }

    public void clear() {
        shoppingCart.clear();
    }
}


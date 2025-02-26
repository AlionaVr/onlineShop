package org.example.repository;

import org.example.entity.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemRepository implements Repository<Items> {
    private final List<Items> itemsList = new ArrayList<>(Arrays.asList(
            new Items("Coffee", 5.5, "Italia"),
            new Items("Tea", 3.0, "India"),
            new Items("Milk", 2.5, "Belarus"),
            new Items("Chocolate", 4.0, "Belarus"),
            new Items("Biscuits", 3.2, "Belarus"),
            new Items("Bread", 1.8, "Belarus")
    ));

    @Override
    public List<Items> getAll() {
        return itemsList;
    }

    @Override
    public void add(Items item) {
        itemsList.add(item);
    }

    @Override
    public void remove(int index) {
        itemsList.remove(index);
    }
}

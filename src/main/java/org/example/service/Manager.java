package org.example.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.entity.Items;
import org.example.repository.ItemRepository;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Manager {

    private final ItemRepository itemRepository;

    public void addNewItem(Items item) {
        itemRepository.add(item);
        System.out.println("Item added to list");
    }

    public void removeFromItemsList(int index) {
        itemRepository.remove(index - 1);
        System.out.println("DELETED");
    }

    public void updateItem(int index, Items newItem) {
        List<Items> items = itemRepository.getAll();
        items.set(index - 1, newItem);
        System.out.println("Item updated successfully.");
    }


    public void showItems() {
        List<Items> items = itemRepository.getAll();
        if (items.isEmpty()) {
            System.out.println("There are no products in the store yet.");
            return;
        }
        System.out.println("List of products in the store:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }
}

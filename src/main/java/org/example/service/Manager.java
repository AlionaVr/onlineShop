package org.example.service;

import org.example.repository.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Items> itemsList = new ArrayList<>(Arrays.asList(
            new Items("Coffee", 5.5, "Italia"),
            new Items("Tea", 3.0, "India"),
            new Items("Milk", 2.5, "Belarus"),
            new Items("Chocolate", 4.0, "Belarus"),
            new Items("Biscuits", 3.2, "Belarus"),
            new Items("Bread", 1.8, "Belarus")
    ));

    public void addNewItem() {
        Items item = enterNewItem();
        itemsList.add(item);
        System.out.println("Item added to list");
    }

    public void removeFromItemsList(int index) {
        if (index < 1 || index > itemsList.size()) {
            System.out.println("Error: Incorrect number.");
            return;
        }
        itemsList.remove(index - 1);
        System.out.println("DELETED");
    }

    public void updateItem(int index) {
        if (index < 1 || index > itemsList.size()) {
            System.out.println("Error: Incorrect number.");
            return;
        }
        itemsList.remove(index - 1);
        addNewItem();
    }

    public void showItems() {
        if (itemsList.isEmpty()) {
            System.out.println("There are no products in the store yet.");
            return;
        }
        System.out.println("List of products in the store:");
        for (int i = 0; i < itemsList.size(); i++) {
            System.out.println((i + 1) + ". " + itemsList.get(i));
        }
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public Items enterNewItem() {
        System.out.println("Please enter data in format: name, price, manufacturer");

        String input = scanner.nextLine();
        String[] parts = input.split(",\\s*");

        if (parts.length != 3) {
            System.out.println("Incorrect format. Please enter: name, price, manufacturer");
            return null;
        }

        String name = parts[0].trim();
        double price;

        try {
            price = Double.parseDouble(parts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Please enter a valid number.");
            return null;
        }

        String manufacturer = parts[2].trim();

        return new Items(name, price, manufacturer);
    }
}

package org.example;

import lombok.RequiredArgsConstructor;
import org.example.entity.Items;

import java.util.Scanner;

@RequiredArgsConstructor
public class ItemFactory {
    private final Scanner scanner;

    public Items createItem() {
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

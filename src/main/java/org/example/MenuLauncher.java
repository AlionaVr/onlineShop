package org.example;

import lombok.RequiredArgsConstructor;
import org.example.entity.Items;
import org.example.service.Customer;
import org.example.service.Manager;

import java.util.Scanner;

@RequiredArgsConstructor
public class MenuLauncher {
    private final int INVALID_INPUT = -1;


    private final Manager manager;
    private final Customer customer;
    private final Scanner scanner;
    private final ItemFactory itemFactory;
    private boolean programRunning = true;


    public void showMainMenu() {
        System.out.println("Welcome to our Shop! Please log in to the system.");

        while (programRunning) {
            System.out.println("""
                    Please, choose the option:
                    1.Manager Login
                    2.Customer Login
                    3.Exit""");

            switch (getUserInput()) {
                case 1 -> showAdminMenu();
                case 2 -> showCustomerMenu();
                case 3 -> exitProgram();
                default -> System.out.println("Incorrect input number");
            }
        }
    }

    private void showAdminMenu() {
        System.out.println("Hello, Manager!");

        while (programRunning) {
            System.out.println("""
                    Please, choose the option:
                    1.Add a new item
                    2.Remove an item
                    3.Update an item
                    4.View all items
                    5.Return to MAIN menu
                    6.Exit""");

            switch (getUserInput()) {
                case 1 -> {
                    Items item = itemFactory.createItem();
                    manager.addNewItem(item);
                }
                case 2 -> {
                    int chosenItemID = getValidChosenItemID(manager.getItemRepository().getAll().size());
                    manager.removeFromItemsList(chosenItemID);
                }
                case 3 -> {
                    int chosenItemID = getValidChosenItemID(manager.getItemRepository().getAll().size());
                    Items item = itemFactory.createItem();
                    manager.updateItem(chosenItemID, item);
                }
                case 4 -> manager.showItems();
                case 5 -> showMainMenu();
                case 6 -> exitProgram();
                default -> System.out.println("Incorrect input number");
            }
        }
    }

    private void showCustomerMenu() {
        System.out.println("Hello, Customer!");

        while (programRunning) {
            System.out.println("""
                    1.Let's shopping!(view items and add to shopping cart)
                    2.View my shopping cart
                    3.Cancel item
                    4.Make the order
                    5.Return to MAIN menu
                    6.Rate a product
                    7.Exit""");

            switch (getUserInput()) {
                case 1 -> addToShoppingCart();
                case 2 -> customer.showShoppingCart();
                case 3 -> showAndCancelItem();
                case 4 -> customer.confirmOrder();
                case 5 -> showMainMenu();
                case 6 -> rateProduct();
                case 7 -> exitProgram();
                default -> System.out.println("Incorrect input number");
            }
        }
    }

    private void rateProduct() {
        int chosenItemID = getValidChosenItemID(manager.getItemRepository().getAll().size());
        double rating = INVALID_INPUT;
        while (rating == INVALID_INPUT) {
            final int MIN_RATING = 0;
            final int MAX_RATING = 5;
            System.out.println("Enter rating (" + MIN_RATING + "-" + MAX_RATING + "): ");
            try {
                rating = Double.parseDouble(scanner.nextLine().trim());
                if (rating < MIN_RATING || rating > MAX_RATING) {
                    System.out.println("Rating must be between " + MIN_RATING + " and " + MAX_RATING);
                    rating = INVALID_INPUT;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating input. Please enter a number.");
                rating = INVALID_INPUT;
            }
            customer.rateItem(chosenItemID, rating);
        }
    }

    private void addToShoppingCart() {
        if (manager.getItemRepository().getAll().isEmpty()) {
            System.out.println("Sorry, no items in the shop");
            return;
        }
        System.out.println("Choose id of one item to add to shopping cart");
        int chosenItemID = getValidChosenItemID(manager.getItemRepository().getAll().size());
        int quantity = getUserQuantity();
        customer.addToShoppingCart(chosenItemID, quantity);
    }

    private void showAndCancelItem() {
        if (customer.getShoppingCart().getAll().isEmpty()) {
            System.out.println("Sorry, your Shopping Cart is Empty ");
            return;
        }
        System.out.println("Choose the number of space, that you would like to cancel. ");
        customer.showShoppingCart();
        int chosenItemID = getValidChosenItemID(customer.getShoppingCart().getAll().size());
        customer.cancelItem(chosenItemID);
    }

    private void exitProgram() {
        System.out.println("Bye, have a nice day!");
        programRunning = false;
    }

    private int getUserInput() {
        int input = INVALID_INPUT;
        while (input == INVALID_INPUT) {
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                input = INVALID_INPUT;
            }
        }
        return input;
    }

    private int getUserQuantity() {
        int quantity = INVALID_INPUT;
        while (quantity == INVALID_INPUT) {
            System.out.println("Please, enter the quantity (must be > 0):");
            quantity = getUserInput();
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                quantity = INVALID_INPUT;
            }
        }
        return quantity;
    }

    private int getValidChosenItemID(int size) {
        manager.showItems();
        int chosenItemID = INVALID_INPUT;
        while (chosenItemID == INVALID_INPUT) {
            System.out.println("Please, choose the id of item");
            chosenItemID = getUserInput();
            final int MIN_ITEM_ID = 1;
            if (chosenItemID < MIN_ITEM_ID || chosenItemID > size) {
                System.out.println("Incorrect number. Please enter a valid item ID.");
                chosenItemID = INVALID_INPUT;
            }
        }
        return chosenItemID;
    }
}
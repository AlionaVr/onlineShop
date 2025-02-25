package org.example;

import lombok.RequiredArgsConstructor;
import org.example.service.Customer;
import org.example.service.Manager;

import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class MenuLauncher {
    private final Manager manager;
    private final Customer customer;
    private final Scanner scanner = new Scanner(System.in);
    private boolean programRunning = true;


    public void showMainMenu() {
        System.out.println("Welcome to our Shop! Please log in to the system.");

        while (programRunning) {
            System.out.println("""
                    Please, choose the option:
                    1.Manager Login
                    2.Customer Login
                    3.Exit""");

            Optional<Integer> optionalInput = Optional.of(Integer.valueOf(scanner.nextLine()));
            int input = optionalInput.orElse(-1);

            switch (input) {
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

            Optional<Integer> optionalInput = Optional.of(Integer.valueOf(scanner.nextLine()));
            int input = optionalInput.orElse(-1);

            switch (input) {
                case 1 -> {
                    manager.addNewItem();
                    manager.showItems();
                }
                case 2 -> {
                    manager.showItems();
                    int chosenItemID = getChosenItemID();
                    manager.removeFromItemsList(chosenItemID);
                }
                case 3 -> {
                    manager.showItems();
                    int chosenItemID = getChosenItemID();
                    manager.updateItem(chosenItemID);
                }
                case 4 -> manager.showItems();
                case 5 -> showMainMenu();
                case 6 -> exitProgram();
                default -> System.out.println("Incorrect input number");
            }
        }
    }

    private int getChosenItemID() {
        System.out.println("Please, choose the id of item");
        return Integer.parseInt(scanner.nextLine());
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
                    6.Exit""");

            Optional<Integer> optionalInput = Optional.of(Integer.valueOf(scanner.nextLine()));
            int input = optionalInput.orElse(-1);

            switch (input) {
                case 1 -> showAndAddToShoppingCart();
                case 2 -> customer.showShoppingCart();
                case 3 -> showAndCancelItem();
                case 4 -> customer.confirmOrder();
                case 5 -> showMainMenu();
                case 6 -> exitProgram();
                default -> System.out.println("Incorrect input number");
            }
        }
    }

    private void showAndAddToShoppingCart() {
        if (manager.getItemsList().isEmpty()) {
            System.out.println("Sorry, no items in the shop");
            return;
        }
        System.out.println("Choose id of one item to add to shopping cart");
        manager.showItems();
        int chosenItemID = getChosenItemID();
        System.out.println("Please, enter quantity of this item: ");
        String bookingDetails = scanner.nextLine().trim();
        customer.addToShoppingCart(chosenItemID, bookingDetails);
    }

    private void showAndCancelItem() {
        if (customer.getShoppingCart().isEmpty()) {
            System.out.println("Sorry, your Shopping Cart is Empty ");
            return;
        }
        System.out.println("Choose the number of space, that you would like to cancel. ");
        customer.showShoppingCart();
        int chosenItemID = getChosenItemID();
        customer.cancelItem(chosenItemID);
    }

    private void exitProgram() {
        System.out.println("Bye, have a nice day!");
        programRunning = false;
    }
}

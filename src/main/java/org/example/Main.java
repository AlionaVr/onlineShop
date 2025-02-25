package org.example;


import org.example.service.Customer;
import org.example.service.Manager;


public class Main {
    private static final Manager manager = new Manager();
    private static final Customer customer = new Customer(manager);

    public static void main(String[] args) {
        MenuLauncher menuLauncher = new MenuLauncher(manager, customer);
        menuLauncher.showMainMenu();
    }
}
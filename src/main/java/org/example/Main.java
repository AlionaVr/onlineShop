package org.example;


import org.example.entity.Items;
import org.example.entity.ShoppingCart;
import org.example.repository.ClearableRepository;
import org.example.repository.ItemRepository;
import org.example.repository.Repository;
import org.example.repository.ShoppingCartRepo;
import org.example.service.Customer;
import org.example.service.Manager;

import java.util.Scanner;


public class Main {
    private static final Repository<Items> itemRepository = new ItemRepository();
    private static final ClearableRepository<ShoppingCart> shoppingCartRepository = new ShoppingCartRepo();
    private static final Manager manager = new Manager(itemRepository);
    private static final Customer customer = new Customer(itemRepository, shoppingCartRepository);
    private static final Scanner scanner = new Scanner(System.in);
    private static final ItemFactory itemFactory = new ItemFactory(scanner);


    public static void main(String[] args) {
        MenuLauncher menuLauncher = new MenuLauncher(manager, customer, scanner, itemFactory);
        menuLauncher.showMainMenu();

        scanner.close();
    }
}

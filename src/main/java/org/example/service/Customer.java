package org.example.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.repository.Items;
import org.example.repository.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Customer {
    private final List<ShoppingCart> shoppingCart = new ArrayList<>();
    private final Manager manager;

    public void addToShoppingCart(int itemId, String quantityInput) {
        try {
            int quantity = Integer.parseInt(quantityInput);
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                return;
            }
            List<Items> itemsList = manager.getItemsList();
            if (itemId < 1 || itemId > itemsList.size()) {
                System.out.println("Invalid item ID.");
                return;
            }
            Items selectedItem = itemsList.get(itemId - 1);
            shoppingCart.add(new ShoppingCart(selectedItem, quantity));
            System.out.println(quantity + " x " + selectedItem.getName() + " added to your cart.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity input.");
        }
    }

    public void cancelItem(int itemId) {
        if (itemId < 1 || itemId > shoppingCart.size()) {
            System.out.println("Invalid item number in the cart.");
            return;
        }

        ShoppingCart removedItem = shoppingCart.remove(itemId - 1);
        System.out.println("Deleted from your shopping Cart");
    }

    public void showShoppingCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }

        System.out.println("Your Shopping Cart:");
        for (int i = 0; i < shoppingCart.size(); i++) {
            ShoppingCart cartItem = shoppingCart.get(i);
            System.out.println((i + 1) + ". " + cartItem.getItem().getName() +
                               " | Quantity: " + cartItem.getQuantity() +
                               " | Price per item: $" + cartItem.getItem().getPrice() +
                               " | Total: $" + (cartItem.getItem().getPrice() * cartItem.getQuantity()));
        }
    }

    public void confirmOrder() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty. Cannot confirm order.");
            return;
        }
        double total = 0.0;
        System.out.println("Order Summary:");
        for (ShoppingCart cartItem : shoppingCart) {
            double itemTotal = cartItem.getItem().getPrice() * cartItem.getQuantity();
            total += itemTotal;
            System.out.println(cartItem.getQuantity() + " x " + cartItem.getItem().getName() +
                               " | $" + itemTotal);
        }
        System.out.println("Total Order Price: $" + total);
        System.out.println("Thank you!");
        shoppingCart.clear();
    }
}

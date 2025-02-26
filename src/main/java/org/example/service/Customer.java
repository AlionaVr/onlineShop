package org.example.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.entity.Items;
import org.example.entity.ShoppingCart;
import org.example.repository.ItemRepository;
import org.example.repository.ShoppingCartRepo;

@RequiredArgsConstructor
@Getter
public class Customer {
    private final ItemRepository itemRepository;
    private final ShoppingCartRepo shoppingCart;

    public void addToShoppingCart(int itemId, int quantity) {
        Items selectedItem = itemRepository.getAll().get(itemId - 1);
        shoppingCart.add(new ShoppingCart(selectedItem, quantity));
        System.out.println(quantity + " x " + selectedItem.getName() + " added to your cart.");
    }

    public void cancelItem(int itemId) {
        shoppingCart.remove(itemId - 1);
        System.out.println("Deleted from your shopping Cart");
    }

    public void showShoppingCart() {
        if (shoppingCart.getAll().isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }

        System.out.println("Your Shopping Cart:");
        for (int i = 0; i < shoppingCart.getAll().size(); i++) {
            ShoppingCart cartItem = shoppingCart.getAll().get(i);
            System.out.println((i + 1) + ". " + cartItem.getItem().getName() +
                               " | Quantity: " + cartItem.getQuantity() +
                               " | Price per item: $" + cartItem.getItem().getPrice() +
                               " | Total: $" + (cartItem.getItem().getPrice() * cartItem.getQuantity()));
        }
    }

    public void confirmOrder() {
        if (shoppingCart.getAll().isEmpty()) {
            System.out.println("Your shopping cart is empty. Cannot confirm order.");
            return;
        }
        double total = 0.0;
        System.out.println("Order Summary:");
        for (ShoppingCart cartItem : shoppingCart.getAll()) {
            double itemTotal = cartItem.getItem().getPrice() * cartItem.getQuantity();
            total += itemTotal;
            System.out.println(cartItem.getQuantity() + " x " + cartItem.getItem().getName() +
                               " | $" + itemTotal);
        }
        System.out.println("Total Order Price: $" + total);
        System.out.println("Thank you!");
        shoppingCart.clear();
    }

    public void rateItem(int itemId, double rating) {
        Items selectedItem = itemRepository.getAll().get(itemId - 1);
        selectedItem.addEvaluation(rating);
        System.out.println("You rated " + selectedItem.getName() + " with " + rating + " stars.");
    }
}

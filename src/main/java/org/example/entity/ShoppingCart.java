package org.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingCart {

    private final Items item;
    private final int quantity;
}


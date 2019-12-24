package com.jgt.pos.utils;

import com.jgt.pos.database.cartdb.Cart;

import java.util.ArrayList;
import java.util.List;

//TODO: remove when done
public class TestUtils {

    public static List<Cart> getTestItemList() {
        List<Cart> testList = new ArrayList<>();
        testList.add(createCartItem("item 1", 500, 2));
        testList.add(createCartItem("item 2", 250, 6));
        testList.add(createCartItem("item 3", 10, 50));
        testList.add(createCartItem("item 4", 60, 30));
        return testList;
    }

    private static Cart createCartItem(String name, int price, int qty) {
        Cart cart = new Cart();
        cart.setName(name);
        cart.setPrice(price);
        cart.setQuantity(qty);
        return cart;
    }
}

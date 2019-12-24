package com.jgt.pos.utils;

import com.jgt.pos.database.cartdb.Cart;

import java.util.ArrayList;
import java.util.List;

//TODO: remove when done
public class TestUtils {

    public static List<Cart> getTestItemList() {
        List<Cart> testList = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            testList.add(createCartItem("item " + i, 60, 30));
        }
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

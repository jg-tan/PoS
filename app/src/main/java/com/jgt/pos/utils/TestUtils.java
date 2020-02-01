package com.jgt.pos.utils;

import com.jgt.pos.database.cart.Cart;
import com.jgt.pos.database.item.Item;

import java.util.ArrayList;
import java.util.List;

//TODO: remove when done
public class TestUtils {

    public static List<Cart> getTestCartList() {
        List<Cart> testList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testList.add(createCartItem("item " + i, 60, 30));
        }
        return testList;
    }

    private static Cart createCartItem(String name, int price, int qty) {
        Cart cart = new Cart(name, price, qty);
        return cart;
    }

    public static List<Item> getTestProductList() {
        List<Item> testList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testList.add(createItem("item " + i, 60 + 5 * i, null));
        }
        return testList;
    }

    private static Item createItem(String name, int price, String icon) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setIcon(icon);
        return item;
    }

}

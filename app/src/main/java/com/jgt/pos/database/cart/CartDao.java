package com.jgt.pos.database.cart;

import com.jgt.pos.database.item.Item;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public abstract class CartDao {
    @Query("SELECT * FROM cart_table " +
            "WHERE cart_name == :cartName")
    abstract Cart getCartItem(String cartName);

    @Insert
    abstract void addCart(Cart cart);

    @Query("DELETE FROM cart_table " +
            "WHERE cart_name == :cartName")
    abstract void deleteFromCart(String cartName);

    @Query("SELECT * FROM cart_table")
    abstract LiveData<List<Cart>> getCart();

    @Query("UPDATE cart_table " +
            "SET cart_quantity = cart_quantity + 1 " +
            "WHERE cart_name == :cartName")
    abstract void addQty(String cartName);

    @Query("UPDATE cart_table " +
            "SET cart_quantity = cart_quantity - 1 " +
            "WHERE cart_name == :cartName")
    abstract void removeQty(String cartName);

    @Query("DELETE FROM cart_table")
    abstract void deleteAll();

    public void addToCart(Item item) {
        String itemName = item.getName();
        int price = item.getPrice();
        Cart cartItem = getCartItem(itemName);
        if (null == cartItem) {
            cartItem = new Cart();
            cartItem.setName(itemName);
            cartItem.setPrice(price);
            cartItem.setQuantity(1);
            addCart(cartItem);
        } else {
            addQty(itemName);
        }
    }

    public void removeFromCart(Item item) {
        String itemName = item.getName();
        Cart cartItem = getCartItem(itemName);
        if (null == cartItem) {
            //do nothing
        } else {
            String cartName = cartItem.getName();
            int qty = cartItem.getQuantity();
            if (1 == qty) {
                deleteFromCart(cartName);
            } else {
                removeQty(cartName);
            }
        }
    }
}

package com.jgt.pos.database.cart;

import android.util.Log;

import com.jgt.pos.database.item.Item;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {
    private static final String TAG = CartViewModel.class.getSimpleName();

    private LiveData<List<Cart>> cartData;

    public void init() {
        if (null != cartData) {
            Log.d(TAG, "ViewModel already initialized.");
            return;
        }
        cartData = CartRepository.getInstance().getCart();
    }

    public LiveData<List<Cart>> getCart() {
        return cartData;
    }

    public void addToCart(Item item) {
        CartRepository.getInstance().addToCart(item);
    }

    public void removeFromCart(Item item) {
        CartRepository.getInstance().removeFromCart(item);
    }

    public void deleteFromCart(Item item) {
        CartRepository.getInstance().deleteFromCart(item.getName());
    }
}

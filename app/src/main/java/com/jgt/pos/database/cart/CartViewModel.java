package com.jgt.pos.database.cart;

import android.util.Log;

import com.jgt.pos.database.item.Item;
import com.jgt.pos.database.sales.Sales;
import com.jgt.pos.database.sales.SalesRepository;

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

    public void deleteAll() {
        CartRepository.getInstance().deleteAll();
    }

    public void saveTransactionHistory() {
        long currentTime = System.currentTimeMillis();
        List<Cart> currentCart = cartData.getValue();

        if (null == currentCart || currentCart.isEmpty()) {
            Log.d(TAG, "@saveTransactionHistory: Cart is empty");
            return;
        }

        for (Cart c : currentCart) {
            Sales s = new Sales(c.getName(),
                    c.getPrice(),
                    currentTime,
                    c.getQuantity());
            SalesRepository.getInstance().insertSalesItem(s);
        }
    }
}

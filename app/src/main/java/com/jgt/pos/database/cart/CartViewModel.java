package com.jgt.pos.database.cart;

import android.util.Log;

import com.jgt.pos.database.item.Item;
import com.jgt.pos.database.sales.Sales;
import com.jgt.pos.database.sales.SalesRepository;
import com.jgt.pos.utils.SharedPref;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {
    private static final String TAG = CartViewModel.class.getSimpleName();

    private LiveData<List<Cart>> cartData;
    private MutableLiveData<Long> priceTotal = new MutableLiveData<>();
    public void init() {
        if (null != cartData) {
            Log.d(TAG, "ViewModel already initialized.");
            return;
        }
        cartData = CartRepository.getInstance().getCart();
        priceTotal.setValue(SharedPref.get().getPriceTotal());
    }

    public LiveData<List<Cart>> getCart() {
        return cartData;
    }

    public LiveData<Long> getPriceTotal() {
        return priceTotal;
    }

    public void addToCart(Item item) {
        CartRepository.getInstance().addToCart(item);
        long curPrice = SharedPref.get().getPriceTotal();
        updatePrice(curPrice + item.getPrice());
    }

    public void removeFromCart(Item item) {
        CartRepository.getInstance().removeFromCart(item);
        long curPrice = SharedPref.get().getPriceTotal();
        updatePrice(curPrice - item.getPrice());
    }

    public void deleteFromCart(Item item) {
        int itemCount = CartRepository.getInstance().deleteFromCart(item.getName());
        long curPrice = SharedPref.get().getPriceTotal();
        updatePrice(curPrice - (item.getPrice() * itemCount));
    }

    public void deleteAll() {
        CartRepository.getInstance().deleteAll();
        updatePrice(0);
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

    private void updatePrice(long total) {
        SharedPref.get().setPriceTotal(total);
        priceTotal.setValue(total);
    }
}

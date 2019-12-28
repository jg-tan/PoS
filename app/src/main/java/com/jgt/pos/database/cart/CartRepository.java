package com.jgt.pos.database.cart;

import com.jgt.pos.database.item.Item;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class CartRepository {
    private static CartRepository instance;
    private static CartDao cartDao;
    private ExecutorService mExecutor;

    private CartRepository() {
        CartDatabase db = CartDatabase.getInstance();
        cartDao = db.cartDao();
        mExecutor = Executors.newFixedThreadPool(2);
    }

    public static CartRepository getInstance() {
        if (null == instance) {
            synchronized (CartRepository.class) {
                instance = new CartRepository();
            }
        }
        return instance;
    }

    public void deleteFromCart(String cartName) {
        mExecutor.submit(() -> cartDao.deleteFromCart(cartName));
    }

    public LiveData<List<Cart>> getCart() {
        try {
            return mExecutor.submit(cartDao::getCart).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addToCart(Item item) {
        mExecutor.submit(() -> cartDao.addToCart(item));
    }

    public void removeFromCart(Item item) {
        mExecutor.submit(() -> cartDao.removeFromCart(item));
    }

    public void deleteAll() {
        mExecutor.submit(cartDao::deleteAll);
    }
}

package com.jgt.pos.database.cart;

public class CartRepository {
    private static CartRepository instance;
    private static CartDao cartDao;

    private CartRepository() {
        CartDatabase db = CartDatabase.getInstance();
        cartDao = db.cartDao();
    }

    public static CartRepository getInstance() {
        if (null == instance) {
            synchronized (CartRepository.class) {
                instance = new CartRepository();
            }
        }
        return instance;
    }
}

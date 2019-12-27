package com.jgt.pos.database.cart;

import com.jgt.pos.utils.ContextManager;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cart.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase instance;

    public static CartDatabase getInstance() {
        if (null == instance) {
            synchronized (CartDatabase.class) {
                instance = Room.databaseBuilder(ContextManager.get(),
                        CartDatabase.class, "cart_databases")
                        .build();
            }
        }
        return instance;
    }

    public abstract CartDao cartDao();
}

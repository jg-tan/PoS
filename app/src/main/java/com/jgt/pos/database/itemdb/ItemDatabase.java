package com.jgt.pos.database.itemdb;

import com.jgt.pos.utils.ContextManager;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {
    private static ItemDatabase instance;

    public static ItemDatabase getInstance() {
        if (null == instance) {
            synchronized (ItemDatabase.class) {
                instance = Room.databaseBuilder(ContextManager.get(),
                        ItemDatabase.class, "item_database")
                        .build();
            }
        }
        return instance;
    }

    public abstract ItemDao itemDao();
}

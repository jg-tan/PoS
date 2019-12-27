package com.jgt.pos.database.sales;

import com.jgt.pos.utils.ContextManager;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sales.class}, version = 1, exportSchema = false)
public abstract class SalesDatabase extends RoomDatabase {
    private static SalesDatabase instance;

    public static SalesDatabase getInstance() {
        if (null == instance) {
            synchronized (SalesDatabase.class) {
                instance = Room.databaseBuilder(ContextManager.get(),
                        SalesDatabase.class, "sales_database")
                        .build();
            }
        }
        return instance;
    }

    public abstract SalesDao salesDao();
}

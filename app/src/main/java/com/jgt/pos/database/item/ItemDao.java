package com.jgt.pos.database.item;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addItem(Item item);

    @Query("DELETE FROM item_table " +
            "WHERE item_name == :itemName")
    void deleteItem(String itemName);

    @Query("SELECT * FROM item_table")
    LiveData<List<Item>> getItems();
}

package com.jgt.pos.database.item;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public abstract class ItemDao {
    private static final String TAG = ItemDao.class.getSimpleName();

    @Insert()
    abstract void insertItem(Item item);

    @Query("DELETE FROM item_table " +
            "WHERE item_name == :itemName")
    abstract void deleteItem(String itemName);

    @Query("SELECT * FROM item_table")
    abstract LiveData<List<Item>> getItems();

    @Query("SELECT * FROM item_table " +
            "WHERE item_id == :id")
    abstract Item getItem(int id);

    @Query("UPDATE item_table " +
            "SET item_name = :itemName, item_price = :itemPrice, item_icon = :itemIcon " +
            "WHERE item_id == :itemId")
    abstract void editItem(int itemId, String itemName, int itemPrice, String itemIcon);

    @Query("SELECT * FROM item_table " +
            "WHERE EXISTS " +
            "(SELECT * FROM item_table " +
            "WHERE item_name == :itemName AND item_price == :itemPrice AND item_icon == :itemIcon)")
    abstract boolean doesItemExist(String itemName, int itemPrice, String itemIcon);

    boolean addItem(Item item) {
        String itemName = item.getName();
        int itemPrice = item.getPrice();
        String itemIcon = item.getIcon();
        if (!doesItemExist(itemName, itemPrice, itemIcon)) {
            insertItem(item);
            return true;
        } else {
            Log.d(TAG, itemName + " already exists" + itemName);
        }
        return false;
    }
}

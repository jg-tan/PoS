package com.jgt.pos.database.item;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class ItemRepository {
    private static ItemRepository instance;
    private static ItemDao itemDao;
    private ExecutorService mExecutor;

    private ItemRepository() {
        ItemDatabase db = ItemDatabase.getInstance();
        itemDao = db.itemDao();
        mExecutor = Executors.newFixedThreadPool(2);
    }

    public static ItemRepository getInstance() {
        if (null == instance) {
            synchronized (ItemRepository.class) {
                instance = new ItemRepository();
            }
        }
        return instance;
    }

    public void addItem(Item item) {
        mExecutor.submit(() -> itemDao.addItem(item));
    }

    public void deleteItem(String itemName) {
        mExecutor.submit(() -> itemDao.deleteItem(itemName));
    }

    public LiveData<List<Item>> getItems() {
        try {
            return mExecutor.submit(itemDao::getItems).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

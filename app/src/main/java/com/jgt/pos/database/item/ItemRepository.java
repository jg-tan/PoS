package com.jgt.pos.database.item;

import java.util.List;
import java.util.concurrent.ExecutionException;
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

    public boolean addItem(Item item) {
        try {
            return mExecutor.submit(() -> itemDao.addItem(item)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteItem(String itemName) {
        mExecutor.submit(() -> itemDao.deleteItem(itemName));
    }

    public void editItem(int itemId, String itemName, int itemPrice, String itemIcon) {
        mExecutor.submit(() -> itemDao.editItem(itemId, itemName, itemPrice, itemIcon));
    }

    public Item getItem(int itemId) {
        try {
            return mExecutor.submit(() -> itemDao.getItem(itemId)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
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

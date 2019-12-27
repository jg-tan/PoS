package com.jgt.pos.database.item;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {
    private static final String TAG = ItemViewModel.class.getSimpleName();

    private LiveData<List<Item>> items;

    public void init() {
        if (null != items) {
            Log.d(TAG, "ViewModel already initialized.");
            return;
        }
        items = ItemRepository.getInstance().getItems();
    }

    public LiveData<List<Item>> getItems() {
        return items;
    }

    public void deleteItem(String itemName) {
        ItemRepository.getInstance().deleteItem(itemName);
    }
}

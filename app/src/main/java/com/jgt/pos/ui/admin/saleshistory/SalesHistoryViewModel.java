package com.jgt.pos.ui.admin.saleshistory;

import android.util.Log;

import com.jgt.pos.database.sales.Sales;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SalesHistoryViewModel extends ViewModel {
    private static final String TAG = SalesHistoryViewModel.class.getSimpleName();
    private SalesHistoryModel model;
    private LiveData<List<Sales>> salesList;

    public void init() {
        if (null != salesList) {
            Log.d(TAG, "ViewModel already initialized.");
            return;
        }
        setModel(new SalesHistoryModel());
        salesList = model.getSalesHistory();
    }

    public LiveData<List<Sales>> getSalesList() {
        return salesList;
    }

    public LiveData<List<Sales>> searchSales(long timestampFrom, long timestampTo) {
        return model.searchSalesHistory(timestampFrom, timestampTo);
    }

    private void setModel(SalesHistoryModel model) {
        this.model = model;
    }
}

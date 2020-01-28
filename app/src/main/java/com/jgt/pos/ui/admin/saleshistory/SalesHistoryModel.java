package com.jgt.pos.ui.admin.saleshistory;

import com.jgt.pos.database.sales.Sales;
import com.jgt.pos.database.sales.SalesRepository;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SalesHistoryModel {
    public LiveData<List<Sales>> getSalesHistory() {
        return SalesRepository.getInstance().getSales();
    }

    public LiveData<List<Sales>> searchSalesHistory(long timestampFrom, long timestampTo) {
        return SalesRepository.getInstance().searchSales(timestampFrom, timestampTo);
    }
}

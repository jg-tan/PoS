package com.jgt.pos.database.sales;

import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class SalesRepository {
    private static SalesRepository instance;
    private static SalesDao salesDao;
    private ExecutorService mExecutor;

    private SalesRepository() {
        SalesDatabase db = SalesDatabase.getInstance();
        salesDao = db.salesDao();
        mExecutor = Executors.newFixedThreadPool(2);
    }

    public static SalesRepository getInstance() {
        if (null == instance) {
            synchronized (SalesRepository.class) {
                instance = new SalesRepository();
            }
        }
        return instance;
    }

    public void insertSalesItem(Sales sales) {
        Log.d("@TEST", "@insert: " + sales.toString());
        mExecutor.submit(() -> salesDao.insertSales(sales));
    }

    public LiveData<List<Sales>> getSales() {
        try {
            LiveData<List<Sales>> s = mExecutor.submit(salesDao::getSales).get();
            Log.d("@TEST", "@getSalesDao: " + (s == null));
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<Sales>> searchSales(long timestampFrom, long timestampTo) {
        try {
            mExecutor.submit(() -> salesDao.searchSales(timestampFrom, timestampTo)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

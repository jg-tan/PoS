package com.jgt.pos.database.sales;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        mExecutor.submit(() -> salesDao.insertSales(sales));
    }
}

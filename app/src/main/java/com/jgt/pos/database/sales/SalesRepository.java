package com.jgt.pos.database.sales;

public class SalesRepository {
    private static SalesRepository instance;
    private static SalesDao salesDao;

    private SalesRepository() {
        SalesDatabase db = SalesDatabase.getInstance();
        salesDao = db.salesDao();
    }

    public static SalesRepository getInstance() {
        if (null == instance) {
            synchronized (SalesRepository.class) {
                instance = new SalesRepository();
            }
        }
        return instance;
    }
}

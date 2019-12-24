package com.jgt.pos.database.itemdb;

public class ItemRepository {
    private static ItemRepository instance;
    private static ItemDao itemDao;

    private ItemRepository() {
        ItemDatabase db = ItemDatabase.getInstance();
        itemDao = db.itemDao();
    }

    public static ItemRepository getInstance() {
        if (null == instance) {
            synchronized (ItemRepository.class) {
                instance = new ItemRepository();
            }
        }
        return instance;
    }
}

package com.jgt.pos.database.sales;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface SalesDao {
    @Insert
    void insertSales(Sales sales);
}

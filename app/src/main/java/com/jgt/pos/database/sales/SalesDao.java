package com.jgt.pos.database.sales;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SalesDao {
    @Insert
    void insertSales(Sales sales);

    @Query("SELECT * FROM sales_table")
    LiveData<List<Sales>> getSales();

    @Query("SELECT * FROM sales_table " +
            "WHERE sales_timestamp >= :timestampFrom AND sales_timestamp <= :timestampTo")
    LiveData<List<Sales>> searchSales(long timestampFrom, long timestampTo);
}

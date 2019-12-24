package com.jgt.pos.database.salesdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sales {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "sales_name")
    private String name;

    @ColumnInfo(name = "sales_price")
    private int price;

    @ColumnInfo(name = "sales_timestamp")
    private long timestamp;

    @ColumnInfo(name = "sales_quantity")
    private int quantity;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

package com.jgt.pos.database.sales;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sales {
    @PrimaryKey
    private int salesId;

    @ColumnInfo(name = "sales_name")
    private String name;

    @ColumnInfo(name = "sales_price")
    private int price;

    @ColumnInfo(name = "sales_timestamp")
    private long timestamp;

    @ColumnInfo(name = "sales_quantity")
    private int quantity;

    public Sales(String name, int price, long timestamp, int quantity) {
        this.name = name;
        this.price = price;
        this.timestamp = timestamp;
        this.quantity = quantity;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

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

    @Override
    public String toString() {
        return "Sales{" +
                "salesId=" + salesId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                ", quantity=" + quantity +
                '}';
    }
}


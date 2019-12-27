package com.jgt.pos.database.cart;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "cart_name")
    private String name;

    @ColumnInfo(name = "cart_price")
    private int price;

    @ColumnInfo(name = "cart_icon", typeAffinity = ColumnInfo.BLOB)
    private byte[] icon;

    @ColumnInfo(name = "cart_quantity")
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

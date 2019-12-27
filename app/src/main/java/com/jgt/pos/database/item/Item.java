package com.jgt.pos.database.item;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@SuppressWarnings("NullableProblems")
@Entity(tableName = "item_table")
public class Item {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "item_name")
    private String name;

    @ColumnInfo(name = "item_price")
    private int price;

    //TODO: Consider saving icon path instead as a STRING
    @ColumnInfo(name = "item_icon", typeAffinity = ColumnInfo.BLOB)
    private byte[] icon;

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
}

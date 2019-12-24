package com.jgt.pos.ui.shop.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.database.itemdb.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopGridAdapter extends RecyclerView.Adapter<ShopGridAdapter.ProductGrViewHolder> {

    List<Item> items;

    @NonNull
    @Override
    public ProductGrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_grid, parent, false);
        return new ProductGrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductGrViewHolder holder, int position) {
        holder.setProductItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        if(null != items) {
            return items.size();
        }
        return 0;
    }

    class ProductGrViewHolder extends RecyclerView.ViewHolder {
        Item item;

        public void setProductItem(Item item) {
            this.item = item;
        }

        public ProductGrViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
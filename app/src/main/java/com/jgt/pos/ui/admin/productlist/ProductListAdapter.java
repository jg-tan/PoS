package com.jgt.pos.ui.admin.productlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.database.itemdb.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListItemViewHolder> {

    List<Item> items;

    @NonNull
    @Override
    public ProductListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.admin_adapter_product_list, parent, false);
        return new ProductListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListItemViewHolder holder, int position) {
        holder.setProductItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        if(null != items) {
            return items.size();
        }
        return 0;
    }

    class ProductListItemViewHolder extends RecyclerView.ViewHolder {
        Item item;

        public void setProductItem(Item item) {
            this.item = item;
        }

        public ProductListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

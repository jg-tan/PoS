package com.jgt.pos.ui.admin.productlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.database.itemdb.ProductItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListItemViewHolder> {

    List<ProductItem> productItems;

    @NonNull
    @Override
    public ProductListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_product_list, parent, false);
        return new ProductListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListItemViewHolder holder, int position) {
        holder.setProductItem(productItems.get(position));
    }

    @Override
    public int getItemCount() {
        if(null != productItems) {
            return productItems.size();
        }
        return 0;
    }

    class ProductListItemViewHolder extends RecyclerView.ViewHolder {
        ProductItem productItem;

        public void setProductItem(ProductItem productItem) {
            this.productItem = productItem;
        }

        public ProductListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

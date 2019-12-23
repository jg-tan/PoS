package com.jgt.pos.ui.productscreen.productgrid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.database.itemdb.ProductItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ProductGrViewHolder> {

    List<ProductItem> productItems;

    @NonNull
    @Override
    public ProductGrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_product_grid, parent, false);
        return new ProductGrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductGrViewHolder holder, int position) {
        holder.setProductItem(productItems.get(position));
    }

    @Override
    public int getItemCount() {
        if(null != productItems) {
            return productItems.size();
        }
        return 0;
    }

    class ProductGrViewHolder extends RecyclerView.ViewHolder {
        ProductItem productItem;

        public void setProductItem(ProductItem productItem) {
            this.productItem = productItem;
        }

        public ProductGrViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
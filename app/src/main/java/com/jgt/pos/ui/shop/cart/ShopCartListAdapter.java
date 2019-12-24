package com.jgt.pos.ui.shop.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCartListAdapter extends RecyclerView.Adapter<ShopCartListAdapter.ProductCartListItemViewHolder> {

    @NonNull
    @Override
    public ProductCartListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_cart, parent, false);
        return new ProductCartListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCartListItemViewHolder holder, int position) {
        //TODO: Think if how to handle CART object
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProductCartListItemViewHolder extends RecyclerView.ViewHolder {

        public ProductCartListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

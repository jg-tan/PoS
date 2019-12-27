package com.jgt.pos.ui.shop.grid;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgt.pos.R;
import com.jgt.pos.database.itemdb.Item;
import com.jgt.pos.ui.admin.productlist.ProductListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopGridAdapter extends RecyclerView.Adapter<ShopGridAdapter.GridItemViewHolder> {

    private List<Item> productList;

    @NonNull
    @Override
    public GridItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shop_adapter_grid, parent, false);
        return new GridItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridItemViewHolder holder, int position) {
        holder.setProductItem(productList.get(position));
    }

    @Override
    public int getItemCount() {
        if (null != productList) {
            return productList.size();
        }
        return 0;
    }

    void setProductList(List<Item> productList) {
        this.productList = productList;
    }

    class GridItemViewHolder extends RecyclerView.ViewHolder {
        Item item;
        TextView tvItemName, tvItemPrice;
        ImageView ivItemIcon;

        GridItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvItemName = itemView.findViewById(R.id.shop_grid_tv_item_name);
            this.tvItemPrice = itemView.findViewById(R.id.shop_grid_tv_item_price);
            this.ivItemIcon = itemView.findViewById(R.id.shop_grid_iv_item_icon);
        }

        @SuppressLint("SetTextI18n")
        void setProductItem(Item item) {
            this.item = item;
            String name = item.getName();
            int price = item.getPrice();
            byte[] icon = item.getIcon();
            tvItemName.setText(name);
            tvItemPrice.setText(Integer.toString(price));
        }
    }
}
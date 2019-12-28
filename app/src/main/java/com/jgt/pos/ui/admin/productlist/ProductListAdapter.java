package com.jgt.pos.ui.admin.productlist;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgt.pos.R;
import com.jgt.pos.database.item.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListItemViewHolder> {

    private List<Item> productList;
    private View.OnClickListener listener;

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
        notifyDataSetChanged();
    }

    void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    class ProductListItemViewHolder extends RecyclerView.ViewHolder {
        Item item;
        TextView tvItemName, tvItemPrice;
        ImageButton btnDelete;
        ImageView ivItemIcon;

        ProductListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvItemName = itemView.findViewById(R.id.admin_product_list_tv_item_name);
            this.tvItemPrice = itemView.findViewById(R.id.admin_product_list_tv_item_price);
            this.btnDelete = itemView.findViewById(R.id.admin_product_list_btn_delete);
            this.ivItemIcon = itemView.findViewById(R.id.admin_product_list_iv_item_icon);
            this.btnDelete.setOnClickListener(listener);
        }

        @SuppressLint("SetTextI18n")
        void setProductItem(Item item) {
            this.item = item;
            this.btnDelete.setTag(item);
            String name = item.getName();
            int price = item.getPrice();
            byte[] icon = item.getIcon();
            tvItemName.setText(name);
            tvItemPrice.setText(Integer.toString(price));
        }
    }
}

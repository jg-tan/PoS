package com.jgt.pos.ui.shop.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jgt.pos.R;
import com.jgt.pos.database.cartdb.Cart;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCartListAdapter extends RecyclerView.Adapter<ShopCartListAdapter.ProductCartListItemViewHolder> {

    private List<Cart> cartItems;

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
        holder.setCartItem(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (null != cartItems) {
            return cartItems.size();
        }
        return 0;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    //TODO: change to live data and add click listeners
    class ProductCartListItemViewHolder extends RecyclerView.ViewHolder {
        Cart cartItem;
        TextView tvItemName, tvItemPriceSingle, tvItemPriceTotal, tvItemQty;
        Button btnAdd, btnRemove, btnDelete;

        public ProductCartListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvItemName = itemView.findViewById(R.id.cart_tv_item_name);
            this.tvItemPriceSingle = itemView.findViewById(R.id.cart_tv_item_price_single);
            this.tvItemPriceTotal = itemView.findViewById(R.id.cart_tv_item_price_total);
            this.tvItemQty = itemView.findViewById(R.id.cart_tv_item_qty);
            this.btnAdd = itemView.findViewById(R.id.cart_btn_add);
            this.btnRemove = itemView.findViewById(R.id.cart_btn_remove);
            this.btnDelete = itemView.findViewById(R.id.cart_btn_delete);
        }

        public void setCartItem(Cart cartItem) {
            this.cartItem = cartItem;
            String name = cartItem.getName();
            int priceSingle = cartItem.getPrice();
            int qty = cartItem.getQuantity();
            int priceTotal = priceSingle*qty;
            tvItemName.setText(name);
            tvItemPriceSingle.setText(Integer.toString(priceSingle));
            tvItemPriceTotal.setText(Integer.toString(priceTotal));
            tvItemQty.setText(Integer.toString(qty));
        }
    }
}

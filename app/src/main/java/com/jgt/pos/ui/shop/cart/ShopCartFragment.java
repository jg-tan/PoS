package com.jgt.pos.ui.shop.cart;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.database.cart.Cart;
import com.jgt.pos.database.cart.CartViewModel;
import com.jgt.pos.database.item.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 1. Get Total Amount
 * - On Init: add sum of items
 * - Set sum in LiveData with int
 * 2. Add or remove items
 * - On Add Clicked:
 * ---- get single price
 * ---- add to LiveData sum
 * - On Remove Clicked:
 * ---- get single price
 * ---- subtract from LiveData sum
 * - On Delete Clicked:
 * ---- get total price
 * ---- subtract from LiveData sum
 */
//TODO: checkout
public class ShopCartFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rvListView;
    private LinearLayoutManager layoutManager;
    private ShopCartListAdapter adapter;
    private View rootView;
    private CartViewModel cartViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GAB", "CART");
        rootView = inflater.inflate(R.layout.shop_fragment_cart, container, false);
        initRecyclerView();
        initViewModels();
        hideButtonsFromLabel();
        return rootView;
    }

    private void initViewModels() {
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        cartViewModel.init();
        cartViewModel.getCart().observe(this, this::onCartChanged);
    }

    private void onCartChanged(List<Cart> cart) {
        adapter.setCartItems(cart);
    }

    private void initRecyclerView() {
        Activity activity = getActivity();
        rvListView = rootView.findViewById(R.id.shop_cart_fragment_rv_cart_items);
        adapter = new ShopCartListAdapter();
        adapter.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(activity);
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
    }

    private void hideButtonsFromLabel() {
        rootView.findViewById(R.id.shop_cart_btn_add_qty).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.shop_cart_btn_remove_qty).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.shop_cart_btn_delete).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Item item = (Item) v.getTag();
        switch (id) {
            case R.id.shop_cart_btn_add_qty:
                cartViewModel.addToCart(item);
                break;
            case R.id.shop_cart_btn_remove_qty:
                cartViewModel.removeFromCart(item);
                break;
            case R.id.shop_cart_btn_delete:
                cartViewModel.deleteFromCart(item);
                break;
            default:
                break;
        }
    }
}

package com.jgt.pos.ui.shop.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jgt.pos.R;
import com.jgt.pos.database.cart.Cart;
import com.jgt.pos.database.cart.CartViewModel;
import com.jgt.pos.database.item.Item;
import com.jgt.pos.ui.shop.ShopActivity;

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
public class ShopCartFragment extends Fragment {
    private static final String TAG = ShopCartFragment.class.getSimpleName();

    private RecyclerView rvListView;
    private LinearLayoutManager layoutManager;
    private ShopCartListAdapter adapter;
    private View rootView;
    private CartViewModel cartViewModel;
    private Button btnCheckout;
    private ShopActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "CART");
        rootView = inflater.inflate(R.layout.shop_fragment_cart, container, false);
        initRecyclerView();
        initViewModels();
        initViews();
        return rootView;
    }

    private void initViews() {
        btnCheckout = rootView.findViewById(R.id.shop_cart_btn_checkout);
        btnCheckout.setOnClickListener(this::onCheckoutClicked);
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
        activity = (ShopActivity) getActivity();
        activity.hideCartButton();
        rvListView = rootView.findViewById(R.id.shop_cart_fragment_rv_cart_items);
        adapter = new ShopCartListAdapter();
        adapter.setOnClickListener(this::onAdapterClicked);
        layoutManager = new LinearLayoutManager(activity);
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);

    }

    private void onCheckoutClicked(View view) {
        cartViewModel.saveTransactionHistory();
        cartViewModel.deleteAll();
    }

    private void onAdapterClicked(View v) {
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

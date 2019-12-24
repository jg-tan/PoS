package com.jgt.pos.ui.shop.cart;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.utils.TestUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
 * */
public class ShopCartFragment extends Fragment {

    private RecyclerView rvListView;
    private LinearLayoutManager layoutManager;
    private ShopCartListAdapter adapter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GAB", "CART");
        rootView = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        initRecyclerView();
        hideButtonsFromLabel();
        return rootView;
    }

    private void initRecyclerView() {
        Activity activity = getActivity();
        rvListView = rootView.findViewById(R.id.shop_cart_fragment_rv_listview);
        adapter = new ShopCartListAdapter();
        layoutManager = new LinearLayoutManager(activity);
        adapter.setCartItems(TestUtils.getTestItemList());
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void hideButtonsFromLabel() {
        rootView.findViewById(R.id.cart_btn_add).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.cart_btn_remove).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.cart_btn_delete).setVisibility(View.INVISIBLE);
    }
}

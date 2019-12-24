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

public class ShopCartFragment extends Fragment {

    private RecyclerView rvListView;
    private LinearLayoutManager layoutManager;
    private ShopCartListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GAB", "CART");
        View root = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        Activity activity = getActivity();
        rvListView = root.findViewById(R.id.shop_cart_fragment_rv_listview);
        adapter = new ShopCartListAdapter();
        layoutManager = new LinearLayoutManager(activity);
        adapter.setCartItems(TestUtils.getTestItemList());
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }
}

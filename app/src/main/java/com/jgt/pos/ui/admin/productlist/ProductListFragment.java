package com.jgt.pos.ui.admin.productlist;

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
 * 1. Show complete lists of registered products
 * 2. ACTION: Long press on item -> Show EDIT FRAGMENT
 * 3. ACTION: Press DELETE button -> Delete item
 */
public class ProductListFragment extends Fragment {

    private RecyclerView rvListView;
    private LinearLayoutManager layoutManager;
    private ProductListAdapter adapter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GAB", "CART");
        rootView = inflater.inflate(R.layout.admin_fragment_product_list, container, false);
        initRecyclerView();
        hideButtonsFromLabel();
        return rootView;
    }

    private void initRecyclerView() {
        Activity activity = getActivity();
        rvListView = rootView.findViewById(R.id.admin_product_list_fragment_rv_item_list);
        adapter = new ProductListAdapter();
        layoutManager = new LinearLayoutManager(activity);
        adapter.setProductList(TestUtils.getTestProductList());
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void hideButtonsFromLabel() {
        rootView.findViewById(R.id.admin_product_list_iv_item_icon).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.admin_product_list_btn_delete).setVisibility(View.INVISIBLE);
    }
}
